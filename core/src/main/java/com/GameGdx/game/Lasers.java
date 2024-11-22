package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Lasers extends Bullet{
    public static float spawnSpeed = 0.2f;
    public static float timer = 0;

    public Lasers(float x,float y){
        sprite.scale = 0.5f;

        sprite.texture = new Texture("laser.png");
        sprite.sprite = new Sprite(sprite.texture);
        sprite.sprite.setScale(sprite.scale);
        sprite.sprite.setX(x);
        sprite.sprite.setY(y);
        rectangle = new Rectangle();

        sprite.width = sprite.sprite.getWidth() * sprite.scale;
        sprite.height = sprite.sprite.getHeight() * sprite.scale;
    }
    public void update(){

    }

}
