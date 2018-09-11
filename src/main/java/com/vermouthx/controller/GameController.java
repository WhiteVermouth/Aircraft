package com.vermouthx.controller;

import com.vermouthx.dto.GameDTO;
import com.vermouthx.entity.bullet.BaseBullet;
import com.vermouthx.entity.plane.BasePlane;
import com.vermouthx.entity.plane.EnemyPlane;
import com.vermouthx.util.FrameUtil;
import com.vermouthx.view.GameFrame;
import com.vermouthx.view.layer.GamePanel;
import com.vermouthx.view.layer.LaunchPanel;

import javax.swing.*;
import java.util.List;

public class GameController {

    private GameDTO dto;

    private JFrame gameFrame;
    private JPanel launchPanel;
    private JPanel gamePanel;

    private PlayerController playerController;

    public GameController() {
        init();
        dto = GameDTO.getDto();
        playerController = new PlayerController(dto.getPlayerPlane(), this);
    }

    private void init() {
        launchPanel = new LaunchPanel(this);
        gameFrame = new GameFrame();
        FrameUtil.setContentPanel(gameFrame, launchPanel);
    }

    public void startGame() {
        dto.setStart(true);
        gamePanel = new GamePanel(this);
        gamePanel.addKeyListener(playerController);
        // listen on player operation and detect collision
        new Thread(() -> {
            while (dto.isStart()) {
                try {
                    playerController.triggerPressedKey();
                    detectCollision();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        GameDTO.getDto().getPlayerPlane().startThread(this);
        // generate enemy plane randomly
        new Thread(() -> {
            while (dto.isStart()) {
                try {
                    EnemyPlane plane = new EnemyPlane();
                    dto.addEnemyPlane(plane);
                    plane.startThread(this);
                    // TODO adjust enemy plane generating speed according to difficulty
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        FrameUtil.setContentPanel(gameFrame, gamePanel);
        gameFrame.repaint();
    }

    /**
     * collision detection
     */
    private void detectCollision() {
        BasePlane playerPlane = dto.getPlayerPlane();
        List<BasePlane> enemyPlanes = dto.getEnemyPlanes();
        List<BaseBullet> playerBullets = dto.getPlayerBullets();
        List<BaseBullet> enemyBullets = dto.getEnemyBullets();
        for (BasePlane enemyPlane : enemyPlanes) {
            if (enemyPlane.getRectangle().intersects(playerPlane.getRectangle())) {
                playerPlane.setDead(true);
                dto.setStart(false);
            }
            for (BaseBullet playerBullet : playerBullets) {
                if (playerBullet.getRectangle().intersects(enemyPlane.getRectangle())) {
                    playerBullet.setHit(true);
                    enemyPlane.setDead(true);
                }
            }
        }
        for (BaseBullet enemyBullet : enemyBullets) {
            if (enemyBullet.getRectangle().intersects(playerPlane.getRectangle())) {
                enemyBullet.setHit(true);
                playerPlane.setDead(true);
                dto.setStart(false);
            }
        }
    }

    public void repaintGamePanel() {
        gamePanel.repaint();
    }
}
