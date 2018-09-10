package com.vermouthx.dto;

import com.vermouthx.entity.BaseBullet;
import com.vermouthx.entity.PlayerPlane;

import java.util.LinkedList;
import java.util.List;

public class GameDTO {

    private String difficulty;

    private PlayerPlane playerPlane;

    private final List<BaseBullet> playerBullets;

    private final List<BaseBullet> enemyBullets;

    public GameDTO() {
        playerPlane = new PlayerPlane();
        playerBullets = new LinkedList<>();
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public PlayerPlane getPlayerPlane() {
        return playerPlane;
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
