package com.vermouthx.entity;

import com.vermouthx.controller.GameController;

import java.awt.image.BufferedImage;

public abstract class BaseBullet extends GameObject {
    private Thread thread;

    public BaseBullet(int x, int y, BufferedImage image) {
        setImage(image);
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
        setX(x - (getWidth() >> 1));
        setY(y - getHeight());
    }

    public abstract void startThread(GameController gameController);

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
