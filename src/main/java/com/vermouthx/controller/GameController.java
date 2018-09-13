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
    private JPanel gamePanel;

    private PlayerController playerController;

    public GameController() {
        init();
        dto = GameDTO.getDto();
        playerController = new PlayerController(dto.getPlayerPlane(), this);
    }

    private void init() {
        gameFrame = new GameFrame();
        FrameUtil.setContentPanel(gameFrame, new LaunchPanel(this));
    }

    public void startGame() {
        dto.setStart(true);
        gamePanel = new GamePanel();
        gamePanel.addKeyListener(playerController);
        // listen on player operation and detect collision
        new Thread(() -> {
            while (dto.isStart()) {
                try {
                    playerController.triggerPressedKey();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        dto.getPlayerPlane().startThread(this);
        // generate enemy plane randomly
        new Thread(() -> {
            while (dto.isStart() && !dto.isBoss()) {
                try {
                    if (!dto.isPause()) {
                        BasePlane plane = new EnemyPlane();
                        dto.addEnemyPlane(plane);
                        plane.startThread(this);
                        Thread.sleep(dto.getDifficulty().getEnemyPlaneGeneratingSpeed());
                    } else {
                        Thread.sleep(dto.getDifficulty().getEnemyPlaneGeneratingSpeed());
                    }
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
                    if (!dto.isPause()) {
                        BaseItem item = new LaserBulletItem();
                        dto.addItem(item);
                        item.startThread(this);
                    } else {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        FrameUtil.setContentPanel(gameFrame, gamePanel);
        gameFrame.repaint();
    }

    public void repaintGamePanel() {
        gamePanel.repaint();
    }
}
