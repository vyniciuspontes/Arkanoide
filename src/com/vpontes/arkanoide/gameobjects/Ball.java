package com.vpontes.arkanoide.gameobjects;

import com.vpontes.arkanoide.scenes.SceneManager;
import java.awt.Graphics;

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
        speedY *= -1;
    }

    @Override
    public void update(float deltaTime) {
        this.x += this.speedX * deltaTime;
        this.y += this.speedY * deltaTime;

        this.collision(deltaTime);
    }

    public void changeDirection() {
        speedY *= -1;
    }

    private void collision(float deltaTime) {
        if (this.x + (this.speedX * deltaTime) < 0) {
            this.speedX *= -1;
        } else if (this.x > 600 - 33 - (this.speedX * deltaTime)) {
            this.speedX *= -1;
        }

        if (this.y + 33 + (this.speedY * deltaTime)< 0) {
            this.speedY *= -1;
        } else if (this.y > 600 - 33 - (this.speedY * deltaTime)) {
            this.speedY *= -1;
            SceneManager.changeScene(0);
        }
    }
    
    @Override
    public void draw(Graphics g){
        super.draw(g);
    }
}
