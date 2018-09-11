package com.vermouthx.entity;

import com.vermouthx.controller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public abstract class BaseBullet extends GameObject {
    private Thread thread;

    public BaseBullet(int x, int y, URL url) {
        try {
            setImage(ImageIO.read(url));
            setWidth(getImage().getWidth());
            setHeight(getImage().getHeight());
            setX(x - (getWidth() >> 1));
            setY(y - getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void startThread(GameController gameController);

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
