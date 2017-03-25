package com.vpontes.arkanoide.core;

import com.vpontes.arkanoide.utils.Canvas;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Game {

    private final Canvas canvas;
    private static Game instance;

    private long lastTime;

    private int fps;
    long a, b;


    private Game() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        this.canvas = new Canvas(gc, 800, 600, "Arkanoide");
        this.lastTime = System.nanoTime();
        instance = null;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void run() {
        long currentTime;
        SceneManager.setup();

        while (true) {
            currentTime = System.nanoTime();

            //Update
            SceneManager.update();

            //Draw
            //this.canvas.getGraphics().drawImage(this.image, (int)this.x, (int)this.y, this.w, this.h, null);
            SceneManager.draw(this.canvas.getGraphics());

            this.canvas.swapBuffers();

            this.lastTime = currentTime;

            // FPS
            a = System.nanoTime();
            fps++;
            if (a - b >= 1000000000) {
                this.canvas.setTitle("Arkanoid - fps: " + fps);
                fps = 0;
                b = a;
            }

            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
