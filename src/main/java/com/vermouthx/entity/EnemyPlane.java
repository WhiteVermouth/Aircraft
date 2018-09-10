package com.vermouthx.entity;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.util.RandomUtil;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class EnemyPlane extends BasePlane {

    public EnemyPlane() {
        try {
            Image image = ImageIO.read(ResourceUtil.getResource("plane/a4-" + RandomUtil.randomInt(1, 6) + ".png"));
            setImage(image);
            setWidth(image.getWidth(null));
            setHeight(image.getHeight(null));
            setX(RandomUtil.randomInt(0, GameConfig.getWindowWidth() - getWidth()));
            setY(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void move(int direction) {

    }

    @Override
    public void shoot(GameController gameController) {

    }


}
