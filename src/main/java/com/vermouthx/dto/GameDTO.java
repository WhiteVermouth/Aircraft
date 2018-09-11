package com.vermouthx.dto;

import com.vermouthx.entity.bullet.BaseBullet;
import com.vermouthx.entity.plane.BasePlane;
import com.vermouthx.entity.plane.PlayerPlane;

import java.util.LinkedList;
import java.util.List;

public class GameDTO {

    private boolean isStart;

    private String difficulty;

    private BasePlane playerPlane;

    private final List<BaseBullet> playerBullets;

    private final List<BasePlane> enemyPlanes;

    private final List<BaseBullet> enemyBullets;

    public GameDTO() {
        playerPlane = new PlayerPlane();
        playerBullets = new LinkedList<>();
        enemyPlanes = new LinkedList<>();
        enemyBullets = new LinkedList<>();
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

    public void removeAllEnemyPlane() {
        synchronized (enemyPlanes) {
            enemyPlanes.clear();
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

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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

    /**
     * single instance dto
     */
    private static GameDTO dto;

    public static GameDTO getDto() {
        if (dto == null)
            dto = new GameDTO();
        return dto;
    }
}
