package com.vermouthx.entity;

import com.vermouthx.controller.GameController;

public abstract class BaseBullet extends GameObject {
    private Thread thread;

    public abstract void startThread(GameController gameController);

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
