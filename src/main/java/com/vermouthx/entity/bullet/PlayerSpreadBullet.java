package com.vermouthx.entity.bullet;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerSpreadBullet extends PlayerBaseBullet {

    private static BufferedImage image;

    static {
        try {
            image = ImageIO.read(ResourceUtil.getResource(GameConfig.getPlayerSpreadBulletPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerSpreadBullet(int x, int y) {
        super(x, y, image);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void move(int direction) {
        super.move(direction);
    }

    @Override
    public void startThread(GameController gameController) {
        super.startThread(gameController);
    }

    @Override
    public void collisionDetect() {
        super.collisionDetect();
    }
}
