package com.vpontes.arkanoide.core;

import com.vpontes.arkanoide.scenes.Congrats;
import com.vpontes.arkanoide.scenes.GameOver;
import com.vpontes.arkanoide.scenes.Level;
import com.vpontes.arkanoide.scenes.Opening;
import com.vpontes.arkanoide.scenes.Scene;
import java.awt.Graphics;

public class SceneManager {

    private SceneManager() {
    }

    private static Scene scene;
    private static SCENE currentScene;

    private static int rows = 1;
    private static int columns = 13;

    public static void setup() {
        scene = new Opening();
        currentScene = SCENE.OPENING;
    }

    public static void changeScene(int button) {
        switch (currentScene) {
            case OPENING:
                switch (button) {
                    case 0:
                        scene = new Level(rows++, columns);
                        currentScene = SCENE.LEVEL;
                        break;

                    case 1:
                        scene = new GameOver();
                        currentScene = SCENE.GAMEOVER;
                        break;
                }

                break;

            case LEVEL:
                switch (button) {
                    case 0:
                        rows = 1;
                        scene = new GameOver();
                        currentScene = SCENE.GAMEOVER;
                        break;

                    default:
                        if (rows <= 5) {
                            scene = new Level(rows++, columns);
                            currentScene = SCENE.LEVEL;
                        } else {
                            rows = 1;
                            scene = new Congrats();
                            currentScene = SCENE.CONGRATS;
                        }
                        break;
                }
                break;

            case CONGRATS:
            case GAMEOVER:
                scene = new Opening();
                currentScene = SCENE.OPENING;
                break;

            default:
                break;
        }
    }

    public static void update() {
        scene.update();
    }

    public static void draw(Graphics g) {
        scene.draw(g);
    }

    enum SCENE {
        OPENING,
        LEVEL,
        GAMEOVER,
        CONGRATS,
    }
}
