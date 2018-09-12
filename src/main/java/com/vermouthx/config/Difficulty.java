package com.vermouthx.config;

import java.util.HashMap;
import java.util.Map;

public class Difficulty {
    private int enemyPlaneGeneratingSpeed;
    private int enemyMovingThreadSpeed;
    private int enemyShootingFrequency;
    private int itemGeneratingFrequency;

    public Difficulty(int enemyPlaneGeneratingSpeed, int enemyMovingThreadSpeed, int enemyShootingFrequency, int itemGeneratingFrequency) {
        this.enemyPlaneGeneratingSpeed = enemyPlaneGeneratingSpeed;
        this.enemyMovingThreadSpeed = enemyMovingThreadSpeed;
        this.enemyShootingFrequency = enemyShootingFrequency;
        this.itemGeneratingFrequency = itemGeneratingFrequency;
    }

    private static Map<String, Difficulty> DIFFICULTY_MAP;

    static {
        DIFFICULTY_MAP = new HashMap<>();
        DIFFICULTY_MAP.put("EASY", new Difficulty(2000, 150, 5000, 10000));
        DIFFICULTY_MAP.put("MEDIUM", new Difficulty(1500, 100, 3000, 20000));
        DIFFICULTY_MAP.put("HARD", new Difficulty(1000, 50, 2000, 30000));
    }

    public static Map<String, Difficulty> getDifficultyMap() {
        return DIFFICULTY_MAP;
    }

    public int getEnemyPlaneGeneratingSpeed() {
        return enemyPlaneGeneratingSpeed;
    }

    public int getEnemyMovingThreadSpeed() {
        return enemyMovingThreadSpeed;
    }

    public int getEnemyShootingFrequency() {
        return enemyShootingFrequency;
    }

    public int getItemGeneratingFrequency() {
        return itemGeneratingFrequency;
    }
}
