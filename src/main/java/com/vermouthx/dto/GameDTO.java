package com.vermouthx.dto;

import com.vermouthx.config.Difficulty;
import com.vermouthx.entity.bullet.BaseBullet;
import com.vermouthx.entity.item.BaseItem;
import com.vermouthx.entity.plane.BasePlane;
import com.vermouthx.entity.plane.PlayerPlane;

import java.util.LinkedList;
import java.util.List;

public class GameDTO {

    private boolean isStart;

    private int score;

    private Difficulty difficulty;

    private BasePlane playerPlane;

    private final List<BaseBullet> playerBullets;

    private final List<BasePlane> enemyPlanes;

    private final List<BaseBullet> enemyBullets;

    private final List<BaseItem> items;

    /**
     * single instance dto
     */
    private static GameDTO dto;

    public static GameDTO getDto() {
        if (dto == null)
            dto = new GameDTO();
        return dto;
    }

    public GameDTO() {
        isStart = false;
        score = 0;
        playerPlane = new PlayerPlane();
        playerBullets = new LinkedList<>();
        enemyPlanes = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        items = new LinkedList<>();
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void addPlayerBullet(BaseBullet bullet) {
        synchronized (playerBullets) {
            playerBullets.add(bullet);
        }
    }

    public void removePlayerBullet(BaseBullet bullet) {
        synchronized (playerBullets) {
            playerBullets.remove(bullet);
        }
    }

    public void addEnemyPlane(BasePlane plane) {
        synchronized (enemyPlanes) {
            enemyPlanes.add(plane);
        }
    }

    public void removeEnemyPlane(BasePlane plane) {
        synchronized (enemyPlanes) {
            enemyPlanes.remove(plane);
        }
    }

    public void addEnemyBullet(BaseBullet bullet) {
        synchronized (enemyBullets) {
            enemyBullets.add(bullet);
        }
    }

    public void removeEnemyBullet(BaseBullet bullet) {
        synchronized (enemyBullets) {
            enemyBullets.remove(bullet);
        }
    }

    public void addItem(BaseItem item) {
        synchronized (items) {
            items.add(item);
        }
    }

    public void removeItem(BaseItem item) {
        synchronized (items) {
            items.remove(item);
        }
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = Difficulty.getDifficultyMap().get(difficulty);
    }

    public BasePlane getPlayerPlane() {
        return playerPlane;
    }

    public List<BasePlane> getEnemyPlanes() {
        return enemyPlanes;
    }

    public List<BaseBullet> getPlayerBullets() {
        return playerBullets;
    }

    public List<BaseBullet> getEnemyBullets() {
        return enemyBullets;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public List<BaseItem> getItems() {
        return items;
    }
}
