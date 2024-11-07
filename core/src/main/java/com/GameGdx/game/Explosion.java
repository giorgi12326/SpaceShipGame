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
        scale= 2.5f;

        texture = new Texture("explode.png");

        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(x);
        sprite.setY(y);
        rectangle = new Rectangle();

        System.out.println(sprite.getHeight() + " " + sprite.getWidth());


        Texture expImage = new Texture("explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,150,60);


        width = 150*scale;
        height = 60*scale;
        System.out.println(width + " " + height);

        animation = new Animation<>(0.1f,expRegion[0]);





    }
}
