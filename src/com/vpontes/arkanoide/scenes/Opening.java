package com.vpontes.arkanoide.scenes;

import com.vpontes.gameframework.core.Game;
import com.vpontes.gameframework.core.Screen;
import com.vpontes.arkanoide.gameobjects.Background;
import com.vpontes.arkanoide.utils.ResourceManager;
import java.awt.Graphics;

public class Opening extends Screen {

    int milliseconds;

    Background bg;

    public Opening(Game game) {
        super(game);
        milliseconds = 0;
        this.bg = new Background(0, 0, 800, 600, ResourceManager.BG_OPENING);
    }

    @Override
    public void update(float deltaTime) {
        //System.out.println("Opening");

        if (milliseconds++ > 50) {
            SceneManager.changeScene(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        this.bg.draw(g);
    }
}
