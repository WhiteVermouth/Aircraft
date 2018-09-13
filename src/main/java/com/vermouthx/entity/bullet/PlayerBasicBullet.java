package com.vermouthx.entity.bullet;

import com.vermouthx.config.Direction;
import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerBasicBullet extends PlayerBaseBullet {
    private static BufferedImage bulletImage;

    static {
        try {
            bulletImage = ImageIO.read(ResourceUtil.getResource(GameConfig.getPlayerBasicBulletPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerBasicBullet(int x, int y) {
        super(x, y, bulletImage);
        setShootAudio(Applet.newAudioClip(ResourceUtil.getResource(GameConfig.getPlayerBasiccBulletSound())));
    }

    @Override
    public void move(int direction) {
        if (direction == Direction.UP && !GameDTO.getDto().isPause()) {
            setY(getY() - GameConfig.getPlayerBulletSpeed());
            collisionDetect();
        }
    }

    @Override
    public void startThread(GameController gameController) {
        setThread(new Thread(() -> {
            while (getY() >= 0 && !isHit()) {
                try {
                    move(Direction.UP);
                    gameController.repaintGamePanel();
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GameDTO.getDto().removePlayerBullet(this);
        }));
        getThread().start();
    }
}
