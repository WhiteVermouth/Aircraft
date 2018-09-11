package com.vermouthx.entity;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
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
                    move(Direction.DOWN);
                    gameController.repaintGamePanel();
                    // TODO adjust move speed according to difficulty
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GameDTO.getDto().removeEnemyPlane(this);
        });
        shootThread = new Thread(() -> {
            while (!isDead()) {
                try {
                    shoot(gameController);
                    // TODO adjust shoot frequency according difficulty
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        moveThread.start();
        shootThread.start();
    }


}
