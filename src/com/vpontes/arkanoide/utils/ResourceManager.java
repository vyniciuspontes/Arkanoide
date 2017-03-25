/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpontes.arkanoide.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Aqui ficam as images a serem usados no jogo
 * @author Vynicius Pontes
 */
public class ResourceManager {
    
    public final static String BLUE_BLOCK = ".//images//azul.png";
    public final static String YELLOW_BLOCK = ".//images//amarelo.png";
    public final static String GREEN_BLOCK = ".//images//verde.png";
    public final static String RED_BLOCK = ".//images//vermelho.png";
    public final static String BACKGROUND = ".//images//background.png";
    public final static String BALL = ".//images//ball.png";
    public final static String PADDLE = ".//images//barra.png";
    public final static String BG_OPENING = ".//images//bgOpening.png";
    public final static String BG_GAME_OVER = ".//images//bgGameOver.png";
    public final static String BG_CONGRATS  = ".//images//bgCongrats.png";
    
    public static Image loadImage(String imageURL){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imageURL));
            return img;
        } catch (IOException e) {
            System.out.println(e.getMessage() + ": Image not found: " + imageURL );
        }
        return null;
    }
}
