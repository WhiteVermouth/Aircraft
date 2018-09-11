package com.vermouthx.entity.plane;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.entity.GameObject;
import com.vermouthx.util.ResourceUtil;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

public abstract class BasePlane extends GameObject {

    private boolean isDead;

    private static Image boomImage;

    private static AudioClip boomAudio;

    static {
        boomImage = new ImageIcon(ResourceUtil.getResource(GameConfig.getBoomGifPath())).getImage();
        boomAudio = Applet.newAudioClip(ResourceUtil.getResource(GameConfig.getBoomAudioPath()));
    }

    public BasePlane() {
        isDead = false;
    }

    public abstract void shoot(GameController gameController);

    public abstract void startThread(GameController gameController);

    public void boom(Graphics g) {
        g.drawImage(boomImage, getX() + (getWidth() >> 1) - (boomImage.getWidth(null) >> 1), getY() + (getHeight() >> 1) - (boomImage.getHeight(null) >> 1), null);
    }

    public void playBoomAudio() {
        boomAudio.play();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

}