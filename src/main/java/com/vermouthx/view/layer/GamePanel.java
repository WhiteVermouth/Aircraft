package com.vermouthx.view.layer;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.entity.BaseBullet;
import com.vermouthx.entity.BasePlane;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends BasePanel {

    /**
     * map image
     */
    private BufferedImage mapImg;

    /**
     * map thread
     */
    private Thread mapThread;

    /**
     * start point y coordinate for map drawing
     */
    private int mapY;

    /**
     * is reach map top
     */
    private boolean isTopMap;

    /**
     * background music
     */
    private AudioClip bgAudioClip;

    public GamePanel(GameController gameController) {
        super(gameController);
        initImage();
        initMusic();
        initMapThread();
    }

    /**
     * load images
     */
    private void initImage() {
        String mapPath = "maps/xingyun1.jpg";
        try {
            mapImg = ImageIO.read(ResourceUtil.getResource(mapPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * play background music
     */
    private void initMusic() {
        bgAudioClip = Applet.newAudioClip(ResourceUtil.getResource("sound/game_music.wav"));
        bgAudioClip.loop();
    }

    /**
     * map moving thread init
     */
    private void initMapThread() {
        mapY = 0;
        isTopMap = false;
        mapThread = new Thread(() -> {
            try {
                while (!isTopMap) {
                    mapY += GameConfig.getMapMoveInterval();
                    Thread.sleep(GameConfig.getMapMoveSpeed());
                    repaint();
                    if (mapY == mapImg.getHeight(null) - GameConfig.getWindowHeight()) {
                        isTopMap = true;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        mapThread.start();
    }

    private void drawMap(Graphics g) {
        g.drawImage(mapImg, 0, 0, GameConfig.getWindowWidth(), GameConfig.getWindowHeight(), 0, mapImg.getHeight(null) - GameConfig.getWindowHeight() - mapY, mapImg.getWidth(null), mapImg.getHeight(null) - mapY, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // draw map
        drawMap(g);
        // draw player plane
        GameDTO.getDto().getPlayerPlane().draw(g);
        // draw player bullets
        synchronized (GameDTO.getDto().getPlayerBullets()) {
            for (BaseBullet baseBullet : GameDTO.getDto().getPlayerBullets())
                baseBullet.draw(g);
        }
        // draw enemy planes
        synchronized (GameDTO.getDto().getEnemyPlanes()) {
            for (BasePlane plane : GameDTO.getDto().getEnemyPlanes()) {
                plane.draw(g);
            }
        }
        // draw enemy bullets
        synchronized (GameDTO.getDto().getEnemyBullets()) {
            for (BaseBullet baseBullet : GameDTO.getDto().getEnemyBullets())
                baseBullet.draw(g);
        }
        requestFocus();
    }
}
