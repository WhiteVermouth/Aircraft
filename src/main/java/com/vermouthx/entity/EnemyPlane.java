package com.vermouthx.entity;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.RandomUtil;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class EnemyPlane extends BasePlane {

    private Thread thread;

    public EnemyPlane() {
        try {
            Image image = ImageIO.read(ResourceUtil.getResource("plane/a4-" + RandomUtil.randomInt(1, 6) + ".png"));
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
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void move(int direction) {
        if (direction == Direction.DOWN) {
            setY(getY() + GameConfig.getEnemyPlaneSpeed());
        }
    }

    @Override
    public void shoot(GameController gameController) {

    }

    public void startThread(GameController gameController) {
        thread = new Thread(() -> {
            while (getY() < GameConfig.getWindowHeight()) {
                try {
                    move(Direction.DOWN);
                    gameController.repaintGamePanel();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GameDTO.getDto().removeEnemyPlane(this);
        });
        thread.start();
    }


}
