package com.vermouthx.entity;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;

public class PlayerBullet extends BaseBullet {

    private static AudioClip audioClip;

    public PlayerBullet(int x, int y) {
        Image image;
        try {
            image = ImageIO.read(ResourceUtil.getResource(GameConfig.getPlaneBulletPath()));
            if (audioClip == null)
                audioClip = Applet.newAudioClip(ResourceUtil.getResource(GameConfig.getPlayerBulletSound()));
            setImage(image);
            setWidth(image.getWidth(null));
            setHeight(image.getHeight(null));
            setX(x - (getWidth() >> 1));
            setY(y - getHeight());
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
        if (direction == Direction.UP) {
            setY(getY() - GameConfig.getPlayerBulletSpeed());
        }
    }

    @Override
    public void startThread(GameController gameController) {
        setThread(new Thread(() -> {
            while (getY() - GameConfig.getPlayerBulletSpeed() >= -getHeight()) {
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
