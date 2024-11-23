package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Lasers extends Bullet{
    public static float spawnSpeed = 0.2f;
    public static float timer = 0;

    public Lasers(float x,float y){
        spriteOfEntity.scale = 0.5f;

        spriteOfEntity.texture = new Texture("laser.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(x);
        spriteOfEntity.sprite.setY(y);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth();
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight();
    }
    public void update(){

    }

}
