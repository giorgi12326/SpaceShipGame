package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Enemy extends Entity{
    public static float moveSpeed = 20f;

    @Override
    public void triggerAnimation() {
        System.out.println("this");
    }
}
