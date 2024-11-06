package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Enemy {
    public Random random = new Random();


    float scale;
    public float width;
    public float height;


    public static float timer = 0f;
    public static float spawnSpeed;

    Texture texture;
    Sprite sprite ;
    Rectangle rectangle;

    public float animationTimer = 0f;
    public Animation<Texture> animation;

    public Enemy(){


    }



}
