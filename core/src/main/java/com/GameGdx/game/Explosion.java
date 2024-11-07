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
        scale= 2f;

        texture = new Texture("explode.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(x);
        sprite.setY(y);
        rectangle = new Rectangle();

        width = sprite.getWidth()*scale;
        height = sprite.getHeight()*scale;

        Texture expImage = new Texture("explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,64,64);
        TextureRegion[] arrRegion = new TextureRegion[expRegion.length*expRegion[0].length];
        System.out.println(arrRegion.length);
        for (int i = 0; i < expRegion.length; i++) {
            for (int j = 0; j < expRegion[0].length; j++) {
                arrRegion[expRegion[0].length*i + j] = expRegion[i][j];
            }
        }

        animation = new Animation<>(0.1f,arrRegion);





    }
}
