package com.vpontes.arkanoide.utils;

import com.vpontes.arkanoide.gameobjects.GameObject;

public class Collision {

    public static boolean collide(GameObject go1, GameObject go2) {
        return go1.getX() < go2.getX() + go2.getW()
                && go2.getX() < go1.getX() + go1.getW()
                && go1.getY() < go2.getY() + go2.getH()
                && go2.getY() < go1.getY()+ go1.getH();
    }
}
