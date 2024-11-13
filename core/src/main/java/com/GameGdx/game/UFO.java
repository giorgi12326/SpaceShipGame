package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class UFO extends Enemy {
    public static float spawnSpeed = 4f;
    public static float timer = 0;
    public static float moveSpeed = 200f;

    UFO(){
        scale = 5.0f;
        animationScale = 4f;

        texture = new Texture("ufo.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(Gdx.graphics.getWidth());
        sprite.setY(random.nextFloat((scale - 1f)/2, Gdx.graphics.getHeight()));
        rectangle = new Rectangle();

        width = sprite.getWidth();
        height = sprite.getHeight();

        hitboxWidth = 18f;
        hitboxHeight = 16f;

        x = sprite.getX();
        y = sprite.getY();

        Texture expImage = new Texture("OmegaBolt.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,100,100);
        animation = new Animation<>(0.1f,expRegion[0]);
        framesOfAnimation = 8;
        shouldDisplayAnimation= false;

    }
    @Override
    public void move(){
        sprite.translateX(-moveSpeed*Gdx.graphics.getDeltaTime());

    }
}
