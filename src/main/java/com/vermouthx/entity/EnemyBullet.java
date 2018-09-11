package com.vermouthx.entity;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.ResourceUtil;

import java.awt.*;

public class EnemyBullet extends BaseBullet {

    public EnemyBullet(int x, int y) {
        super(x, y, ResourceUtil.getResource(GameConfig.getEnemyBulletPath()));
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
            while (getY() < GameConfig.getWindowHeight() + getHeight()) {
                try {
                    move(Direction.DOWN);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GameDTO.getDto().removeEnemyBullet(this);
            System.out.println(GameDTO.getDto().getEnemyBullets().size());
        }));
        getThread().start();
    }
}
