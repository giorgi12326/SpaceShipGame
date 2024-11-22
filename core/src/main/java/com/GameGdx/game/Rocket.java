package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Rocket extends Bullet {
    public static float spawnSpeed = 1f;
    public static float timer = 0;

    public Rocket(float x,float y){
        sprite.scale = 3f;

        sprite.texture = new Texture("rocket.png");
        sprite.sprite = new Sprite(sprite.texture);
        sprite.sprite.setScale(sprite.scale);
        sprite.sprite.setX(x);
        sprite.sprite.setY(y);

        sprite.width = sprite.sprite.getWidth() * sprite.scale;
        sprite.height = sprite.sprite.getHeight() * sprite.scale;




    }
}
