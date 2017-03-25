package com.vpontes.arkanoide.utils;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

public class Canvas {

    private JFrame frame;
    private BufferStrategy bs;
    private int screenWidth, screenHeight;
    private String title;

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }

    public void addKeyListener(KeyListener listener){
        frame.addKeyListener(listener);
    }
    
    public Canvas(GraphicsConfiguration gc, int screenWidth, int screenHeight, String title) {
        frame = new JFrame(gc);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.title = title;
        init();
    }

    private void init() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setTitle(this.title);

        // avoid potential deadlock in 1.4.1_02
        try {
            EventQueue.invokeAndWait(() -> {
                frame.createBufferStrategy(2);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
        }

        this.bs = frame.getBufferStrategy();

    }

    public Graphics getGraphics() {
        if (bs == null) {
            return null;
        }
        return this.bs.getDrawGraphics();
    }

    public void swapBuffers() {
        this.bs.show();
        getGraphics().dispose();
        Toolkit.getDefaultToolkit().sync();
    }
}
