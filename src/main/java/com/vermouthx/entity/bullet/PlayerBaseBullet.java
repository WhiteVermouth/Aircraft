package com.vermouthx.entity.bullet;

import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.entity.plane.BasePlane;

import java.awt.image.BufferedImage;
import java.util.List;

public class PlayerBaseBullet extends BaseBullet {

    public PlayerBaseBullet(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    @Override
    public void startThread(GameController gameController) {
    }

    @Override
    public void move(int direction) {

    }

    @Override
    public void collisionDetect() {
        synchronized (GameDTO.getDto().getEnemyPlanes()) {
            for (BasePlane enemyPlane : GameDTO.getDto().getEnemyPlanes()) {
                if (!enemyPlane.isDead() && getRectangle().intersects(enemyPlane.getRectangle())) {
                    setHit(true);
                    enemyPlane.setDead(true);
                }
            }
        }
    }
}
