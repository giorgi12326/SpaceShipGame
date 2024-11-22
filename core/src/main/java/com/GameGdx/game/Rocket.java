package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rocket extends Bullet {
    public static float spawnSpeed = 1f;
    public static float timer = 0;

    public Rocket(float x,float y){
        spriteOfEntity.scale = 3f;

        spriteOfEntity.texture = new Texture("rocket.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(x);
        spriteOfEntity.sprite.setY(y);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight() * spriteOfEntity.scale;




    }
}
