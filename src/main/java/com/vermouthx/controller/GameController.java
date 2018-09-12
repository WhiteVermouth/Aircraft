package com.vermouthx.controller;

import com.vermouthx.dto.GameDTO;
import com.vermouthx.entity.item.BaseItem;
import com.vermouthx.entity.item.LaserBulletItem;
import com.vermouthx.entity.plane.BasePlane;
import com.vermouthx.entity.plane.EnemyPlane;
import com.vermouthx.util.FrameUtil;
import com.vermouthx.view.GameFrame;
import com.vermouthx.view.layer.GamePanel;
import com.vermouthx.view.layer.LaunchPanel;

import javax.swing.*;

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
//                    detectCollision();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        dto.getPlayerPlane().startThread(this);
        // generate enemy plane randomly
        new Thread(() -> {
            while (dto.isStart()) {
                try {
                    BasePlane plane = new EnemyPlane();
                    dto.addEnemyPlane(plane);
                    plane.startThread(this);
                    Thread.sleep(dto.getDifficulty().getEnemyPlaneGeneratingSpeed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // generate item randomly
        new Thread(() -> {
            while (dto.isStart()) {
                try {
                    // TODO: item generating randomly
                    Thread.sleep(dto.getDifficulty().getItemGeneratingFrequency());
                    BaseItem item = new LaserBulletItem();
                    dto.addItem(item);
                    item.startThread(this);
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
    private synchronized void detectCollision() {
        synchronized (dto.getEnemyPlanes()) {
            for (BasePlane enemyPlane : dto.getEnemyPlanes()) {
                synchronized (dto.getPlayerPlane()) {
                    if (!enemyPlane.isDead() && enemyPlane.getRectangle().intersects(dto.getPlayerPlane().getRectangle())) {
                        dto.getPlayerPlane().setDead(true);
                        dto.setStart(false);
                    }
                }
            }
        }
    }

    public void repaintGamePanel() {
        gamePanel.repaint();
    }
}
