package com.vermouthx.view.layer;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.dto.GameDTO;
import com.vermouthx.entity.bullet.BaseBullet;
import com.vermouthx.entity.item.BaseItem;
import com.vermouthx.entity.plane.BasePlane;
import com.vermouthx.util.LayerUtil;
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
     * score ui image
     */
    private BufferedImage scoreUIImg;
    private int scorePadding;

    /**
     * boss warning image
     */
    private BufferedImage bossWarnImg;

    /**
     * map thread
     */
    private Thread mapThread;

    /**
     * start point y coordinate for map drawing
     */
    private int mapY;

    /**
     * boss warn state
     */
    private boolean isBossWarn;

    /**
     * boss battle state
     */
    private boolean isBossBattle;

    /**
     * is reach map top
     */
    private boolean isTopMap;

    /**
     * background music
     */
    private AudioClip bgAudioClip;

    /**
     * boss warning sound
     */
    private AudioClip bossWarningAudioClip;

    /**
     * boss battle music
     */
    private AudioClip bossBattleAudioClip;

    public GamePanel(GameController gameController) {
        super(gameController);
        isBossWarn = false;
        isBossBattle = false;
        scorePadding = GameConfig.getPadding();
        initImage();
        initMusic();
        initMapThread();
    }

    /**
     * load images
     */
    private void initImage() {
        String mapPath = "maps/xingyun1.jpg";
        String scoreUIPath = "score/uiScore.png";
        String bossWarnImgPath = "boss/smsX.png";
        try {
            mapImg = ImageIO.read(ResourceUtil.getResource(mapPath));
            scoreUIImg = ImageIO.read(ResourceUtil.getResource(scoreUIPath));
            bossWarnImg = ImageIO.read(ResourceUtil.getResource(bossWarnImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * play background music
     */
    private void initMusic() {
        bgAudioClip = Applet.newAudioClip(ResourceUtil.getResource("sound/game_music.wav"));
        bossWarningAudioClip = Applet.newAudioClip(ResourceUtil.getResource("sound/bosswarning.wav"));
        bossBattleAudioClip = Applet.newAudioClip(ResourceUtil.getResource("sound/bossbeijing.wav"));
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
                    if (!isBossWarn && mapY > ((mapImg.getHeight() - GameConfig.getWindowWidth()) / 10 * 7)) {
                        isBossWarn = true;
                        bgAudioClip.stop();
                        bossWarningAudioClip.loop();
                    }
                    if (mapY == mapImg.getHeight() - GameConfig.getWindowHeight()) {
                        isTopMap = true;
                        isBossWarn = false;
                        isBossBattle = true;
                        bossWarningAudioClip.stop();
                        bossBattleAudioClip.loop();
                    }
                    repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        mapThread.start();
    }

    private void drawMap(Graphics g) {
        g.drawImage(mapImg, 0, 0, GameConfig.getWindowWidth(), GameConfig.getWindowHeight(), 0, mapImg.getHeight() - GameConfig.getWindowHeight() - mapY, mapImg.getWidth(), mapImg.getHeight() - mapY, null);
    }

    private void drawBossWarning(Graphics g) {
        if (isBossWarn) {
            g.drawImage(bossWarnImg, (GameConfig.getWindowWidth() >> 1) - (bossWarnImg.getWidth() >> 1), GameConfig.getWindowHeight() / 3, null);
        }
    }

    private void drawBoss(Graphics g) {
        if (isBossBattle) {
            GameDTO.getDto().getBossPlane().draw(g);
        }
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
        // draw items
        synchronized (GameDTO.getDto().getItems()) {
            for (BaseItem item : GameDTO.getDto().getItems())
                item.draw(g);
        }
        // draw score
        g.drawImage(scoreUIImg, scorePadding, scorePadding, null);
        LayerUtil.drawNumber(g, (scorePadding << 1) + scoreUIImg.getWidth(), scorePadding, GameDTO.getDto().getScore());
        // draw boss
        drawBossWarning(g);
        drawBoss(g);
        requestFocus();
    }
}
