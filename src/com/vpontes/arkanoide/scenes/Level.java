package com.vpontes.arkanoide.scenes;

import com.vpontes.gameframework.core.Game;
import com.vpontes.gameframework.core.Screen;
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

public class Level extends Screen {

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
    private final Paddle paddle;
    
    private final Random randomColor;

    private final List<Rect> rects = new ArrayList<>();

    private final String[] blocksImages = {
        ResourceManager.BLUE_BLOCK,
        ResourceManager.YELLOW_BLOCK,
        ResourceManager.GREEN_BLOCK,
        ResourceManager.RED_BLOCK
    };

    public Level(Game game, int rows, int columns) {
        super(game);
        this.bg = new Background(0, 0, 800, 600, ResourceManager.BACKGROUND);
        this.ball = new Ball(384, 284, 33, 33, ResourceManager.BALL, 100f);
        this.paddle = new Paddle(384, 500, 100, 15, ResourceManager.PADDLE, 100f);
        this.game.addKeyListener(paddle);
        randomColor = new Random();
        
        setupRects(rows, columns);
    }

    /**
     * Organiza os retangulos em suas posicoes iniciais
     */
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
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {

        ball.update(deltaTime);
        paddle.update(deltaTime);

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
     * Atualiza a cada frame desenhando os objetos
     * @param deltaTime
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        
        bg.draw(g);
        ball.draw(g);
        paddle.draw(g);

        rects.forEach((rect) -> {
            rect.draw(g);
        });
    }
}
