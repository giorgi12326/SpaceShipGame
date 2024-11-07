package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Rocket extends Bullet {
    public static float spawnSpeed = 1f;
    public static float timer = 0;

    public Rocket(float x,float y){
        scale= 3f;

        texture = new Texture("rocket.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(x);
        sprite.setY(y);
        rectangle = new Rectangle();

        width = sprite.getWidth()*scale;
        height = sprite.getHeight()*scale;




    }
}
