package com.vpontes.arkanoide.gameobjects;

import com.vpontes.arkanoide.utils.ResourceManager;
import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObject {

    private boolean disabled;
    protected Image image;
    protected float x, y;
    protected int w, h;

    public Image getImage() {
        return image;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public GameObject(float x, float y, int w, int h, String image) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.image = ResourceManager.loadImage(image);
    }
    
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }

    public void draw(Graphics g) {
        if(!disabled)
            g.drawImage(this.image, (int) x, (int) y, w, h, null);
    }
}
