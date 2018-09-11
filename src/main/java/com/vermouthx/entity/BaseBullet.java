package com.vermouthx.entity;

import com.vermouthx.controller.GameController;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseBullet extends GameObject {
    private Thread thread;
    private boolean isHit;

    public BaseBullet(int x, int y, BufferedImage image) {
        isHit = false;
        setImage(image);
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
        setX(x - (getWidth() >> 1));
        setY(y - getHeight());
    }

    @Override
    public void draw(Graphics g) {
        if (!isHit())
            g.drawImage(getImage(), getX(), getY(), null);
    }

    public abstract void startThread(GameController gameController);

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
