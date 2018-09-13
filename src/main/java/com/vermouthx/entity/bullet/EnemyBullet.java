package com.vermouthx.entity.bullet;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.config.Direction;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
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
    public void move(int direction) {
        if (direction == Direction.DOWN && !GameDTO.getDto().isPause()) {
            setY(getY() + GameConfig.getEnemyBulletSpeed());
            collisionDetect();
        }
    }

    @Override
    public void startThread(GameController gameController) {
        setThread(new Thread(() -> {
            while (getY() <= GameConfig.getWindowHeight() - (getHeight()) && !isHit()) {
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

    @Override
    public void collisionDetect() {
        synchronized (GameDTO.getDto().getPlayerPlane()) {
            if (getRectangle().intersects(GameDTO.getDto().getPlayerPlane().getRectangle())) {
                setHit(true);
                GameDTO.getDto().getPlayerPlane().setDead(true);
                GameDTO.getDto().setStart(false);
            }
        }
    }
}
