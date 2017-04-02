/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpontes.arkanoide.game;

import com.vpontes.arkanoide.scenes.Opening;
import com.vpontes.gameframework.core.Game;
import com.vpontes.arkanoide.scenes.SceneManager;
import com.vpontes.arkanoide.scenes.SceneManager.SCREENS;
import com.vpontes.gameframework.core.Screen;

/**
 *
 * @author Vynicius Pontes
 */
public class Main extends Game{
    
    public static void main(String args[]) {
        new Main(800, 600, "Arkanoide").run();
    }
    
    public Main(int screenWidth, int screenHeight, String windowTitle) {
        super(screenWidth, screenHeight, windowTitle);
    }

    @Override
    public Screen getFirstScreen() {
        SceneManager.setup(this, SCREENS.OPENING);
        return new Opening(this);
    }
}
