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

    /**
     * Background da tela de jogo
     */
    private GameObject bg;
    /**
     * Bola do jogo
     */
    private Ball ball;
    /**
     * Palheta do jogo
     */
    private Paddle paddle;
    
    private Random randomColor;

    /**
     * Lista que agrupa os gameObjects do jogo
     */
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<Rect> rects = new ArrayList<>();

    private String[] blocksImages = {
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
        
        setupRects(rows, columns);
        gameObjects.add(this.bg);
        gameObjects.add(this.ball);
        gameObjects.add(this.paddle);
    }

    private void setupRects(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (rows <= 4) {
                    this.rects.add(new Rect(40 * (j + 1) + (j),
                            70 + (i + 1) * 30, 40, 30, blocksImages[i % 4]));
                } else {
                    this.rects.add(new Rect(40 * (j + 1) + (j),
                            70 + (i + 1) * 30, 40, 30, blocksImages[randomColor.nextInt(blocksImages.length)]));
                }
            }
        }
    }

    /**
     * Atualiza a cada frame dando dinamismo as ações dos objetos
     */
    @Override
    public void update() {

        ball.update();
        paddle.update();

        //caso a bola colida com a palheta a bola muda de direcao
        if (Collision.collide(this.ball, this.paddle)) {
            ball.changeDirection();
        }

        //Itera os retangulos para verificar se a bola os colidiu
        //Caso sim o retangulo e removido da lista e a bola muda de direcao
        for (Iterator<Rect> i = rects.iterator(); i.hasNext();) {
            Rect rect = i.next();
            if (Collision.collide(this.ball, rect)) {
                ball.changeDirection();
                i.remove();
            }
        }

        //Caso os retnagulos acabem o jogo avanca o level
        if (rects.size() <= 0) {
            SceneManager.changeScene(444);
        }
    }

    /**
     * Atualiza a cada frame desenhando as imagens dos objetos
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        
        gameObjects.forEach((go) -> {
            go.draw(g);
        });

        rects.forEach((rect) -> {
            rect.draw(g);
        });
    }
}
