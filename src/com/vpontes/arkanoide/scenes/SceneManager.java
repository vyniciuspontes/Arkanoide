package com.vpontes.arkanoide.scenes;

import com.vpontes.gameframework.core.Game;
import com.vpontes.gameframework.core.Screen;

public class SceneManager {

    private SceneManager() {
    }

    private static Screen scene;
    private static SCREENS currentScene;

    private static int rows = 1;
    private static int columns = 13;

    private static Game game;

    public static void setup(Game newGame, SCREENS startScene) {
        game = newGame;
        currentScene = startScene;
    }

    public static void changeScene(int action) {
        switch (currentScene) {
            case OPENING:
                switch (action) {
                    case 0:
                        scene = new Level(game, rows++, columns);
                        currentScene = SCREENS.LEVEL;
                        break;

                    case 1:
                        scene = new GameOver(game);
                        currentScene = SCREENS.GAMEOVER;
                        break;
                }

                break;

            case LEVEL:
                switch (action) {
                    case 0:
                        rows = 1;
                        scene = new GameOver(game);
                        currentScene = SCREENS.GAMEOVER;
                        break;

                    default:
                        if (rows <= 5) {
                            scene = new Level(game, rows++, columns);
                            currentScene = SCREENS.LEVEL;
                        } else {
                            rows = 1;
                            scene = new Congrats(game);
                            currentScene = SCREENS.CONGRATS;
                        }
                        break;
                }
                break;

            case CONGRATS:
            case GAMEOVER:
                scene = new Opening(game);
                currentScene = SCREENS.OPENING;
                break;

            default:
                break;
        }
        game.setScreen(scene);
    }

    public enum SCREENS {
        OPENING,
        LEVEL,
        GAMEOVER,
        CONGRATS,
    }
}
