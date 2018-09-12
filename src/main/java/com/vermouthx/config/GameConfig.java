package com.vermouthx.config;

import java.util.HashMap;
import java.util.Map;

public class GameConfig {

    private GameConfig() {

    }

    /**
     * game name
     */
    private static final String GAME_NAME = "Aircraft";
    /**
     * window width
     */
    private static final int WINDOW_WIDTH = 520;
    /**
     * window height
     */
    private static final int WINDOW_HEIGHT = 700;
    /**
     * number of difficulty
     */
    private static final int DIFFICULTY_DEGREE = 3;
    /**
     * map for int and difficulty (String)
     */
    private static Map<Integer, String> DIFFICULT_MAP = new HashMap<>();

    /**
     * interval pixel for map moving
     */
    private static int MAP_MOVE_INTERVAL = 1;

    /**
     * speed for map moving (millisecond)
     */
    private static int MAP_MOVE_SPEED = 50;

    /**
     * image for player plane
     */
    private static String PLAYER_IMG_PATH = "plane/p01-1.png";
    private static int PLAYER_PLANE_LEFT_EDGE_START_X = 0;
    private static int PLAYER_PLANE_LEFT_EDGE_END_X = 53;
    private static int PLAYER_PLANE_LEFT_START_X = 53;
    private static int PLAYER_PLANE_LEFT_END_X = 118;
    private static int PLAYER_PLANE_STILL_START_X = 118;
    private static int PLAYER_PLANE_STILL_END_X = 196;
    private static int PLAYER_PLANE_RIGHT_START_X = 196;
    private static int PLAYER_PLANE_RIGHT_END_X = 263;
    private static int PLAYER_PLANE_RIGHT_EDGE_START_X = 263;
    private static int PLAYER_PLANE_RIGHT_EDGE_END_X = 318;

    /**
     * image for player bullet
     */
    private static String PLAYER_BASIC_BULLET_PATH = "bullet/p-f01.png";
    private static String PLAYER_LASER_BULLET_PATH = "bullet/laser.png";
    private static String PLAYER_SPREAD_BULLET_PATH = "bullet/p-f02.png";

    /**
     * image for player bullet item
     */
    private static String PLAYER_LASER_BULLET_ITEM_PATH = "bullet/laser_item.png";

    /**
     * player item moving speed
     */
    private static int PLAYER_ITEM_MOVING_SPEED = 10;

    /**
     * player item thread sleep interval
     */
    private static int PLAYER_ITEM_THREAD_SLEEP = 30;

    /**
     * player plane speed
     */
    private static int PLAYER_PLANE_SPEED = 20;

    /**
     * player bullet speed
     */
    private static int PLAYER_BULLET_SPEED = 5;

    /**
     * player bullet sound
     */
    private static String PLAYER_BULLET_SOUND = "sound/bullet.wav";

    /**
     * enemy plane speed
     */
    private static int ENEMY_PLANE_SPEED = 5;

    /**
     * image for enemy bullet
     */
    private static String ENEMY_BULLET_PATH = "bullet/jizhong1.png";

    /**
     * enemy bullet speed
     */
    private static int ENEMY_BULLET_SPEED = 10;

    /**
     * boom gif
     */
    private static String BOOM_GIF_PATH = "boom/boom.gif";

    /**
     * boom gif duration
     */
    private static int BOOM_GIF_DURATION = 1200;

    /**
     * boom sound
     */
    private static String BOOM_AUDIO_PATH = "sound/baozha.wav";

    static {
        // fill difficulty map
        DIFFICULT_MAP.put(0, "EASY");
        DIFFICULT_MAP.put(1, "MEDIUM");
        DIFFICULT_MAP.put(2, "HARD");
    }

    public static String getGameName() {
        return GAME_NAME;
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public static int getDifficultyDegree() {
        return DIFFICULTY_DEGREE;
    }

    public static Map<Integer, String> getDifficultMap() {
        return DIFFICULT_MAP;
    }

    public static int getMapMoveInterval() {
        return MAP_MOVE_INTERVAL;
    }

    public static int getMapMoveSpeed() {
        return MAP_MOVE_SPEED;
    }

    public static String getPlayerImgPath() {
        return PLAYER_IMG_PATH;
    }

    public static void setPlayerImgPath(String playerImgPath) {
        PLAYER_IMG_PATH = playerImgPath;
    }

    public static int getPlayerPlaneLeftEdgeStartX() {
        return PLAYER_PLANE_LEFT_EDGE_START_X;
    }

    public static void setPlayerPlaneLeftEdgeStartX(int playerPlaneLeftEdgeStartX) {
        PLAYER_PLANE_LEFT_EDGE_START_X = playerPlaneLeftEdgeStartX;
    }

    public static int getPlayerPlaneLeftEdgeEndX() {
        return PLAYER_PLANE_LEFT_EDGE_END_X;
    }

    public static void setPlayerPlaneLeftEdgeEndX(int playerPlaneLeftEdgeEndX) {
        PLAYER_PLANE_LEFT_EDGE_END_X = playerPlaneLeftEdgeEndX;
    }

    public static int getPlayerPlaneLeftStartX() {
        return PLAYER_PLANE_LEFT_START_X;
    }

    public static void setPlayerPlaneLeftStartX(int playerPlaneLeftStartX) {
        PLAYER_PLANE_LEFT_START_X = playerPlaneLeftStartX;
    }

    public static int getPlayerPlaneLeftEndX() {
        return PLAYER_PLANE_LEFT_END_X;
    }

    public static void setPlayerPlaneLeftEndX(int playerPlaneLeftEndX) {
        PLAYER_PLANE_LEFT_END_X = playerPlaneLeftEndX;
    }

    public static int getPlayerPlaneStillStartX() {
        return PLAYER_PLANE_STILL_START_X;
    }

    public static void setPlayerPlaneStillStartX(int playerPlaneStillStartX) {
        PLAYER_PLANE_STILL_START_X = playerPlaneStillStartX;
    }

    public static int getPlayerPlaneStillEndX() {
        return PLAYER_PLANE_STILL_END_X;
    }

    public static void setPlayerPlaneStillEndX(int playerPlaneStillEndX) {
        PLAYER_PLANE_STILL_END_X = playerPlaneStillEndX;
    }

    public static int getPlayerPlaneRightStartX() {
        return PLAYER_PLANE_RIGHT_START_X;
    }

    public static void setPlayerPlaneRightStartX(int playerPlaneRightStartX) {
        PLAYER_PLANE_RIGHT_START_X = playerPlaneRightStartX;
    }

    public static int getPlayerPlaneRightEndX() {
        return PLAYER_PLANE_RIGHT_END_X;
    }

    public static void setPlayerPlaneRightEndX(int playerPlaneRightEndX) {
        PLAYER_PLANE_RIGHT_END_X = playerPlaneRightEndX;
    }

    public static int getPlayerPlaneRightEdgeStartX() {
        return PLAYER_PLANE_RIGHT_EDGE_START_X;
    }

    public static void setPlayerPlaneRightEdgeStartX(int playerPlaneRightEdgeStartX) {
        PLAYER_PLANE_RIGHT_EDGE_START_X = playerPlaneRightEdgeStartX;
    }

    public static int getPlayerPlaneRightEdgeEndX() {
        return PLAYER_PLANE_RIGHT_EDGE_END_X;
    }

    public static void setPlayerPlaneRightEdgeEndX(int playerPlaneRightEdgeEndX) {
        PLAYER_PLANE_RIGHT_EDGE_END_X = playerPlaneRightEdgeEndX;
    }

    public static int getPlayerPlaneSpeed() {
        return PLAYER_PLANE_SPEED;
    }

    public static String getPlayerBasicBulletPath() {
        return PLAYER_BASIC_BULLET_PATH;
    }

    public static String getPlayerLaserBulletPath() {
        return PLAYER_LASER_BULLET_PATH;
    }

    public static String getPlayerSpreadBulletPath() {
        return PLAYER_SPREAD_BULLET_PATH;
    }

    public static int getPlayerBulletSpeed() {
        return PLAYER_BULLET_SPEED;
    }

    public static String getPlayerBulletSound() {
        return PLAYER_BULLET_SOUND;
    }

    public static int getEnemyPlaneSpeed() {
        return ENEMY_PLANE_SPEED;
    }

    public static String getEnemyBulletPath() {
        return ENEMY_BULLET_PATH;
    }

    public static int getEnemyBulletSpeed() {
        return ENEMY_BULLET_SPEED;
    }

    public static String getBoomGifPath() {
        return BOOM_GIF_PATH;
    }

    public static String getBoomAudioPath() {
        return BOOM_AUDIO_PATH;
    }

    public static int getBoomGifDuration() {
        return BOOM_GIF_DURATION;
    }

    public static String getPlayerLaserBulletItemPath() {
        return PLAYER_LASER_BULLET_ITEM_PATH;
    }

    public static int getPlayerItemMovingSpeed() {
        return PLAYER_ITEM_MOVING_SPEED;
    }

    public static int getPlayerItemThreadSleep() {
        return PLAYER_ITEM_THREAD_SLEEP;
    }
}
