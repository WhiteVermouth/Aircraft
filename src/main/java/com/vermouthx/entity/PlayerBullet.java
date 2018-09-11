package com.vermouthx.entity;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.util.ResourceUtil;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

public class PlayerBullet extends BaseBullet {

    private static AudioClip audioClip;

    public PlayerBullet(int x, int y) {
        super(x, y, ResourceUtil.getResource(GameConfig.getPlaneBulletPath()));
        if (audioClip == null)
            audioClip = Applet.newAudioClip(ResourceUtil.getResource(GameConfig.getPlayerBulletSound()));
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
            while (getY() >= -getHeight()) {
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
