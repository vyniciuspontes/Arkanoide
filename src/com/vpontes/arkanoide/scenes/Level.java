package com.vpontes.arkanoide.scenes;

import com.vpontes.arkanoide.core.SceneManager;
import com.vpontes.arkanoide.gameobjects.Background;
import com.vpontes.arkanoide.gameobjects.Ball;
import com.vpontes.arkanoide.utils.Collision;
import com.vpontes.arkanoide.gameobjects.GameObject;
import com.vpontes.arkanoide.gameobjects.Paddle;
import com.vpontes.arkanoide.gameobjects.Rect;
import com.vpontes.arkanoide.utils.ResourceManager;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Level extends Scene {

    private int rows;
    private int columns;

    private GameObject bg;
    private Ball ball;
    private Paddle paddle;
    private Random randomColor;

    private List<GameObject> gameObjects = new ArrayList<>();
    private List<Rect> rects = new ArrayList<>();

    String[] blocksImages = {
        ResourceManager.BLUE_BLOCK,
        ResourceManager.YELLOW_BLOCK,
        ResourceManager.GREEN_BLOCK,
        ResourceManager.RED_BLOCK
    };

    public Level(int rows, int columns) {

        this.bg = new Background(0, 0, 800, 600, ResourceManager.BACKGROUND);
        this.ball = new Ball(384, 284, 33, 33, ResourceManager.BALL, 10);
        this.paddle = new Paddle(384, 500, 100, 15, ResourceManager.PADDLE, 20);
        randomColor = new Random();

        gameObjects.add(this.bg);
        gameObjects.add(this.ball);
        gameObjects.add(this.paddle);

        this.rows = rows;
        this.columns = columns;
        setupRects();
    }

    private void setupRects() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.rows <= 4) {
                    this.rects.add(new Rect(40 * (j + 1) + (j),
                            70 + (i + 1) * 30, 40, 30, blocksImages[i % 4]));
                } else {
                    this.rects.add(new Rect(40 * (j + 1) + (j),
                            70 + (i + 1) * 30, 40, 30, blocksImages[randomColor.nextInt(blocksImages.length)]));
                }
            }
        }
    }

    @Override
    public void update() {

        ball.update();
        paddle.update();

        if (Collision.collide(this.ball, this.paddle)) {
            ball.changeDirection();
        }

        for (Iterator<Rect> i = rects.iterator(); i.hasNext();) {
            Rect rect = i.next();
            if (Collision.collide(this.ball, rect)) {
                ball.changeDirection();
                i.remove();
            }
        }

        if (rects.size() <= 0) {
            SceneManager.changeScene(444);
        }
    }

    @Override
    public void draw(Graphics g) {
        this.bg.draw(g);

        gameObjects.forEach((go) -> {
            go.draw(g);
        });

        rects.forEach((go) -> {
            go.draw(g);
        });
    }
}
