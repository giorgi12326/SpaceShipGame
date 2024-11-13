package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Explosion extends Bullet{
    public static float spawnSpeed = 1f;
    public static float timer = 0;
    public boolean expired = false;

    public Explosion(float x,float y){
        scale= 8f;

        texture = new Texture("explode.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(x);
        sprite.setY(y);

        height = sprite.getHeight();
        width = sprite.getWidth();

        hitboxWidth = height;
        hitboxHeight = height;

        rectangle = new Rectangle();

        Texture expImage = new Texture("explode.png");

        width = 150f;
        height  = 60f;

        TextureRegion[][] expRegion = TextureRegion.split(expImage,(int)width,(int)height);
        animation = new Animation<>(0.1f,expRegion[0]);
        shouldDisplayAnimation = true;
        framesOfAnimation = 5;

    }


}
