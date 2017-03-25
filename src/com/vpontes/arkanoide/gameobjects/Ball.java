package com.vpontes.arkanoide.gameobjects;

import com.vpontes.arkanoide.core.SceneManager;

public class Ball extends GameObject implements Dynamic {

    public float speedX;
    private float speedY;

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public Ball(float x, float y, int w, int h, String image, float speed) {
        super(x, y, w, h, image);

        this.speedX = this.speedY = speed;
    }

    @Override
    public void update() {
        this.x += this.speedX;
        this.y += this.speedY;

        this.collision();
    }

    public void changeDirection() {
        speedY *= -1;
    }

    private void collision() {
        if (this.x < 0) {
            this.speedX *= -1;
        } else if (this.x > 600 - 32) {
            this.speedX *= -1;
        }

        if (this.y < 0) {
            this.speedY *= -1;
        } else if (this.y > 600 - 32) {
            this.speedY *= -1;
            SceneManager.changeScene(0);
        }
    }
}
