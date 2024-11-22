package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends Bullet{
    public static float spawnSpeed = 1f;
    public static float timer = 0;
    public boolean expired = false;

    public Explosion(float x,float y){
        sprite.scale = 8f;

        sprite.texture = new Texture("explode.png");
        sprite.sprite = new Sprite(sprite.texture);
        sprite.sprite.setScale(sprite.scale);
        sprite.sprite.setX(x);
        sprite.sprite.setY(y);

        sprite.height = sprite.sprite.getHeight();
        sprite.width = sprite.sprite.getWidth();

        hitboxOfEntity.hitboxWidth = sprite.height;
        hitboxOfEntity.hitboxHeight = sprite.height;


        Texture expImage = new Texture("explode.png");

        sprite.width = 150f;
        sprite.height = 60f;

        TextureRegion[][] expRegion = TextureRegion.split(expImage,(int) sprite.width,(int) sprite.height);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.shouldDisplayAnimation = 0;
        animationOfEntity.framesOfAnimation = 5;

    }

    @Override
    public void hitBoxDuringAnimation() {
        hitboxOfEntity.setRectangle();
    }
}
