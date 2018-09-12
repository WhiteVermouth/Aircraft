package com.vermouthx.entity.plane;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BossPlane extends BasePlane {

    private BufferedImage normalStateImg;
    private BufferedImage hitStateImg;
    private BufferedImage deadStateImg;

    public BossPlane() {
        try {
            normalStateImg = ImageIO.read(ResourceUtil.getResource(GameConfig.getBossPlanePath()));
            hitStateImg = ImageIO.read(ResourceUtil.getResource(GameConfig.getBossPlaneHitPath()));
            deadStateImg = ImageIO.read(ResourceUtil.getResource(GameConfig.getBossPlaneDeadPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImage(normalStateImg);
        setWidth(normalStateImg.getWidth());
        setHeight(normalStateImg.getHeight());
        setX((GameConfig.getWindowWidth() >> 1) - (getWidth() >> 1));
        setY(GameConfig.getPadding() << 2);
    }

    @Override
    public void shoot(GameController gameController) {

    }

    @Override
    public void startThread(GameController gameController) {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void move(int direction) {

    }

    public BufferedImage getImage() {
        return normalStateImg;
    }
}
