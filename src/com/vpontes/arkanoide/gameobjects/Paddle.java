package com.vpontes.arkanoide.gameobjects;

import com.vpontes.arkanoide.core.Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends GameObject implements KeyListener, Dynamic {

    boolean LeftPressed = false;
    boolean RightPressed = false;
    int velocidade;

    public Paddle(float x, float y, int w, int h, String image, int velocidade) {
        super(x, y, w, h, image);
        Game.getInstance().getCanvas().addKeyListener(this);
        this.velocidade = velocidade;
    }

    @Override
    public void update() {
        // Input
        if (LeftPressed) {
            this.x -= velocidade;
        }
        if (RightPressed) {
            this.x += velocidade;
        }
        this.fixedLimits();
    }

    private void fixedLimits() {
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x + this.w > 600) {
            this.x = 600 - this.w;
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
            LeftPressed = true;
        }

        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
            RightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {

        if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
            LeftPressed = false;
        }

        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
            RightPressed = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
