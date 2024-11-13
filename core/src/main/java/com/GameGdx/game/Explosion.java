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
        scale= 4f;

        texture = new Texture("explode.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(x);
        sprite.setY(y);
        rectangle = new Rectangle();

        Texture expImage = new Texture("explode.png");

        width = 150;
        height  = 60;

        TextureRegion[][] expRegion = TextureRegion.split(expImage,(int)width,(int)height);
        animation = new Animation<>(0.1f,expRegion[0]);
        shouldDisplayAnimation = true;
        framesOfAnimation = 5;

    }

}
