package com.vermouthx.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    /**
     * x coordinate
     */
    private int x;
    /**
     * y coordinate
     */
    private int y;

    /**
     * width
     */
    private int width;

    /**
     * height
     */
    private int height;

    /**
     * image
     */
    private BufferedImage image;

    public GameObject() {
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public abstract void draw(Graphics g);

    public abstract void move(int direction);

}
