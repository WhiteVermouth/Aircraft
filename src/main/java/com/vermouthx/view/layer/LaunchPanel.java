package com.vermouthx.view.layer;

import com.vermouthx.config.GameConfig;
import com.vermouthx.controller.GameController;
import com.vermouthx.util.ResourceUtil;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LaunchPanel extends BasePanel {

    /**
     * background image
     */
    private Image background;
    /**
     * background curated plane
     */
    private Image planeMenu;
    /**
     * icon for button 'tap to continue'
     */
    private ImageIcon btnContinueIcon;
    /**
     * icons for button 'select difficulty'
     */
    private ImageIcon[] btnDifficultyIcons;
    /**
     * 'button' continue
     */
    private JLabel btnContinue;
    /**
     * 'buttons' select difficulty
     */
    private JLabel[] btnDifficulty;

    /**
     * background music player
     */
    private AudioClip bgAudioClip;

    /**
     * button click sound
     */
    private AudioClip btnAudioClip;

    public LaunchPanel(GameController gameController) {
        super(gameController);
        btnDifficultyIcons = new ImageIcon[GameConfig.getDifficultyDegree()];
        btnDifficulty = new JLabel[GameConfig.getDifficultyDegree()];
        initImage();
        initBtnContinue();
        initMusic();
    }

    /**
     * load add the images except button image
     */
    private void initImage() {
        setLayout(null);
        String bgPath = "menu/bg_menu.jpg";
        String planeMenuPath = "menu/plane_menu.png";
        String btnContinueIconPath = "menu/letter_cover.png";
        try {
            background = ImageIO.read(ResourceUtil.getResource(bgPath));
            planeMenu = ImageIO.read(ResourceUtil.getResource(planeMenuPath));
            btnContinueIcon = new ImageIcon(ResourceUtil.getResource(btnContinueIconPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * play background music
     */
    private void initMusic() {
        bgAudioClip = Applet.newAudioClip(ResourceUtil.getResource("sound/beijing1.wav"));
        bgAudioClip.loop();
        btnAudioClip = Applet.newAudioClip(ResourceUtil.getResource("sound/menuselect.wav"));
    }

    /**
     * initialize button continue
     */
    private void initBtnContinue() {
        btnContinue = new JLabel(btnContinueIcon);
        btnContinue.setBounds((GameConfig.getWindowWidth() - btnContinueIcon.getIconWidth()) >> 1, GameConfig.getWindowHeight() / 3 * 2, btnContinueIcon.getIconWidth(), btnContinueIcon.getIconHeight());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(btnContinue);
                btnAudioClip.play();
                initBtnDifficulty();
            }
        });
        add(btnContinue);
    }

    /**
     * initialize buttons that select difficulty
     */
    private void initBtnDifficulty() {
        String btnDifficultyImagesBasePath = "menu/stageClassWord";
        for (int i = 0; i < GameConfig.getDifficultyDegree(); i++) {
            btnDifficultyIcons[i] = new ImageIcon(ResourceUtil.getResource(btnDifficultyImagesBasePath + i + ".png"));
            btnDifficulty[i] = new JLabel(btnDifficultyIcons[i]);
            btnDifficulty[i].setBounds(((GameConfig.getWindowWidth() - btnDifficultyIcons[i].getIconWidth() * 3) >> 2) + i * (btnDifficultyIcons[i].getIconWidth() + ((GameConfig.getWindowWidth() - btnDifficultyIcons[i].getIconWidth() * 3) >> 2)), GameConfig.getWindowHeight() / 5 * 3, btnDifficultyIcons[i].getIconWidth(), btnDifficultyIcons[i].getIconHeight());
            final int difficulty = i;
            btnDifficulty[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    gameController.getDto().setDifficulty(GameConfig.getDifficultMap().get(difficulty));
                    btnAudioClip.play();
                    bgAudioClip.stop();
                    gameController.startGame();
                }
            });
            add(btnDifficulty[i]);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // draw background
        g.drawImage(background, 0, 0, GameConfig.getWindowWidth(), GameConfig.getWindowHeight(), null);
        // draw curated plane
        g.drawImage(planeMenu, 0, 0, GameConfig.getWindowWidth(), planeMenu.getHeight(null), 0, 0, 490, planeMenu.getHeight(null), null);
        requestFocus();
    }
}
