package com.vpontes.arkanoide.scenes;

import com.vpontes.gameframework.core.Game;
import com.vpontes.gameframework.core.Screen;
import com.vpontes.arkanoide.gameobjects.Background;
import com.vpontes.arkanoide.utils.ResourceManager;
import java.awt.Graphics;

public class Congrats extends Screen {

    int i;

    Background bg;

    public Congrats(Game game) {
        super(game);
        this.i = 0;

        this.bg = new Background(0, 0, 800, 600, ResourceManager.BG_CONGRATS);
    }

    @Override
    public void update(float deltaTime) {
        //System.out.println("Congrats");

        if (i++ > 1000) {
            SceneManager.changeScene(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        this.bg.draw(g);
    }
}
