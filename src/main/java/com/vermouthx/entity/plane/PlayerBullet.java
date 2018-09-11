package com.vermouthx.entity.plane;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.entity.Direction;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerBullet extends BaseBullet {

    private static AudioClip audioClip;

    private static BufferedImage bulletImage;

    static {
        try {
            bulletImage = ImageIO.read(ResourceUtil.getResource(GameConfig.getPlayerBulletPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerBullet(int x, int y) {
        super(x, y, bulletImage);
        if (audioClip == null)
            audioClip = Applet.newAudioClip(ResourceUtil.getResource(GameConfig.getPlayerBulletSound()));
    }

    @Override
    public void move(int direction) {
        if (direction == Direction.UP) {
            setY(getY() - GameConfig.getPlayerBulletSpeed());
        }
    }

    @Override
    public void startThread(GameController gameController) {
        setThread(new Thread(() -> {
            while (getY() >= -getHeight() && !isHit()) {
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

    public static AudioClip getAudioClip() {
        return audioClip;
    }
}
