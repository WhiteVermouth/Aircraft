package com.vermouthx.config;

import java.util.HashMap;
import java.util.Map;

public class GameConfig {

    /**
     * game name
     */
    private static final String GAME_NAME = "Aircraft";
    /**
     * window width
     */
    private static final int WINDOW_WIDTH = 380;
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
     * player plane speed
     */
    private static int PLAYER_PLANE_SPEED = 20;

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
}
