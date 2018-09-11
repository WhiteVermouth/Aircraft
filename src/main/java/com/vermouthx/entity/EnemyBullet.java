package com.vermouthx.entity;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyBullet extends BaseBullet {

    private static BufferedImage bulletImage;

    static {
        try {
            bulletImage = ImageIO.read(ResourceUtil.getResource(GameConfig.getEnemyBulletPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EnemyBullet(int x, int y) {
        super(x, y, bulletImage);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void move(int direction) {
        if (direction == Direction.DOWN) {
            setY(getY() + GameConfig.getEnemyBulletSpeed());
        }
    }

    @Override
    public void startThread(GameController gameController) {
        setThread(new Thread(() -> {
            while (getY() <= GameConfig.getWindowHeight() - (getHeight())) {
                try {
                    move(Direction.DOWN);
                    gameController.repaintGamePanel();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GameDTO.getDto().removeEnemyBullet(this);
        }));
        getThread().start();
    }
}
