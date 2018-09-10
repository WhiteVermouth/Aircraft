package com.vermouthx.entity;

import com.vermouthx.controller.GameController;

import java.applet.AudioClip;

public abstract class BaseBullet extends GameObject {
    private Thread thread;
    private AudioClip audioClip;

    public AudioClip getAudioClip() {
        return audioClip;
    }

    public void setAudioClip(AudioClip audioClip) {
        this.audioClip = audioClip;
    }

    public abstract void startThread(GameController gameController);

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
