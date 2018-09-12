package com.vermouthx.entity.bullet;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerLaserBullet extends PlayerBaseBullet {

    private static BufferedImage bulletImage;

    static {
        try {
            bulletImage = ImageIO.read(ResourceUtil.getResource(GameConfig.getPlayerLaserBulletPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerLaserBullet(int x, int y) {
        super(x, y, bulletImage);
        setY(0);
        setHeight(y);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void startThread(GameController gameController) {
        setThread(new Thread(() -> {
            try {
                collisionDetect();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GameDTO.getDto().removePlayerBullet(this);
            gameController.repaintGamePanel();
        }));
        getThread().start();
    }
}
