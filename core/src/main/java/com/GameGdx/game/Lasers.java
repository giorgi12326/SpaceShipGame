package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Lasers extends Bullet{
    public static float spawnSpeed = 0.2f;
    public static float timer = 0;

    public Lasers(float x,float y){
        scale= 0.5f;

        texture = new Texture("laser.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(x);
        sprite.setY(y);
        rectangle = new Rectangle();

        width = sprite.getWidth()*scale;
        height = sprite.getHeight()*scale;
    }
    public void update(){

    }
}
