package com.vpontes.arkanoide.scenes;

import com.vpontes.arkanoide.gameobjects.Background;
import com.vpontes.arkanoide.core.SceneManager;
import com.vpontes.arkanoide.utils.ResourceManager;
import java.awt.Graphics;

public class GameOver extends Scene {

    int i;

    Background bg;

    public GameOver() {
        this.i = 0;

        this.bg = new Background(0, 0, 800, 600, ResourceManager.BG_GAME_OVER);
    }

    @Override
    public void update() {
        //System.out.println("GameOver");

        if (i++ > 50) {
            SceneManager.changeScene(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        this.bg.draw(g);
    }
}
