package com.vermouthx.controller;

import com.vermouthx.entity.plane.BasePlane;
import com.vermouthx.entity.Direction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class PlayerController extends KeyAdapter {

    private BasePlane playerPlane;
    private GameController gameController;
    private Set<Character> functionKeys;
    private Set<Character> pressedKeys;

    public PlayerController(BasePlane playerPlane, GameController gameController) {
        this.playerPlane = playerPlane;
        this.gameController = gameController;
        functionKeys = new HashSet<>();
        char[] funCharKeys = {'e', 'd', 's', 'f', 'j'};
        for (char c : funCharKeys)
            functionKeys.add(c);
        pressedKeys = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (functionKeys.contains(key)) {
            pressedKeys.add(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if (functionKeys.contains(key)) {
            pressedKeys.remove(key);
        }
        if (pressedKeys.size() == 0 || (pressedKeys.size() == 1 && pressedKeys.contains('j'))) {
            playerPlane.move(Direction.STILL);
            gameController.repaintGamePanel();
        }
    }

    public void triggerPressedKey() {
        if (pressedKeys.size() <= 3) {
            if (pressedKeys.contains('e')) playerPlane.move(Direction.UP);
            if (pressedKeys.contains('d')) playerPlane.move(Direction.DOWN);
            if (pressedKeys.contains('s')) playerPlane.move(Direction.LEFT);
            if (pressedKeys.contains('f')) playerPlane.move(Direction.RIGHT);
            if (pressedKeys.contains('j')) playerPlane.shoot(gameController);
            gameController.repaintGamePanel();
        }
    }
}
