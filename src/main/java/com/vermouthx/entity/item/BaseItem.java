package com.vermouthx.entity.item;

import com.vermouthx.config.Direction;
import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.entity.GameObject;
import com.vermouthx.entity.bullet.PlayerLaserBullet;
import com.vermouthx.util.ResourceUtil;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseItem extends GameObject {

    private boolean isGot;
    private Thread thread;
    private AudioClip gotAudio;

    private Map<String, String> bulletMap;

    public BaseItem() {
        isGot = false;
        bulletMap = new HashMap<>();
        bulletMap.put("LaserBulletItem", PlayerLaserBullet.class.getName());
        gotAudio = Applet.newAudioClip(ResourceUtil.getResource("sound/upgrade.wav"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void move(int direction) {
        if (direction == Direction.DOWN && !GameDTO.getDto().isPause()) {
            setY(getY() + GameConfig.getPlayerItemMovingSpeed());
            collisionDetect();
        }
    }

    public abstract void startThread(GameController gameController);

    public boolean isGot() {
        return isGot;
    }

    public void setGot(boolean got) {
        isGot = got;
        if (got)
            gotAudio.play();
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void collisionDetect() {
        synchronized (GameDTO.getDto().getPlayerPlane()) {
            if (GameDTO.getDto().getPlayerPlane().getRectangle().intersects(getRectangle())) {
                setGot(true);
                GameDTO.getDto().getPlayerPlane().setBulletClass(bulletMap.get(getClass().getSimpleName()));
            }
        }
    }
}
