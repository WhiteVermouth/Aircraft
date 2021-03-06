package com.vermouthx.entity.plane;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.config.Direction;
import com.vermouthx.entity.bullet.BaseBullet;
import com.vermouthx.entity.bullet.EnemyBullet;
import com.vermouthx.util.RandomUtil;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyPlane extends BasePlane {

    private Thread moveThread;
    private Thread shootThread;

    public EnemyPlane() {
        super();
        try {
            BufferedImage image = ImageIO.read(ResourceUtil.getResource("plane/a4-" + RandomUtil.randomInt(1, 6) + ".png"));
            setImage(image);
            setWidth(image.getWidth(null));
            setHeight(image.getHeight(null));
            setX(RandomUtil.randomInt(0, GameConfig.getWindowWidth() - getWidth()));
            setY(-getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!isDead())
            g.drawImage(getImage(), getX(), getY(), null);
        else
            boom(g);
    }

    @Override
    public void move(int direction) {
        if (direction == Direction.DOWN) {
            setY(getY() + GameConfig.getEnemyPlaneSpeed());
        }
    }

    @Override
    public void shoot(GameController gameController) {
        BaseBullet bullet = new EnemyBullet(getX() + (getWidth() >> 1), getY() + getHeight());
        GameDTO.getDto().addEnemyBullet(bullet);
        bullet.startThread(gameController);
    }

    public void startThread(GameController gameController) {
        moveThread = new Thread(() -> {
            while (getY() < GameConfig.getWindowHeight() && !isDead()) {
                try {
                    if (!GameDTO.getDto().isPause()) {
                        move(Direction.DOWN);
                        gameController.repaintGamePanel();
                        Thread.sleep(GameDTO.getDto().getDifficulty().getEnemyMovingThreadSpeed());
                    } else {
                        Thread.sleep(GameDTO.getDto().getDifficulty().getEnemyMovingThreadSpeed());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (isDead()) {
                try {
                    playBoomAudio();
                    GameDTO.getDto().addScore(GameDTO.getDto().getDifficulty().getScorePerEnemy());
                    Thread.sleep(GameConfig.getBoomGifDuration());
                    GameDTO.getDto().removeEnemyPlane(this);
                    gameController.repaintGamePanel();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                GameDTO.getDto().removeEnemyPlane(this);
            }
        });
        // enemy plane auto shooting
        shootThread = new Thread(() -> {
            while (!isDead()) {
                try {
                    if (!GameDTO.getDto().isPause()) {
                        shoot(gameController);
                        Thread.sleep(GameDTO.getDto().getDifficulty().getEnemyShootingFrequency());
                    } else {
                        Thread.sleep(GameDTO.getDto().getDifficulty().getEnemyShootingFrequency());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        moveThread.start();
        shootThread.start();
    }

}
