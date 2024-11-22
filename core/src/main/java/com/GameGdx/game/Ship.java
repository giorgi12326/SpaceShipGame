package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends Entity{

    public static float moveSpeed = 200f;

    Ship(){
        sprite.scale = 3.0f;
        sprite.texture = new Texture("plane.png");
        sprite.sprite = new Sprite(sprite.texture);
        sprite.sprite.setScale(sprite.scale);
        sprite.sprite.setX(Gdx.graphics.getWidth()/2f);
        sprite.sprite.setY(200);

        sprite.width = sprite.sprite.getWidth();
        sprite.height = sprite.sprite.getHeight();

        hitboxOfEntity.hitboxWidth = 17f;
        hitboxOfEntity.hitboxHeight = 16f;

        Texture expImage = new Texture("PhaseLeft.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,16,16);
        animationOfEntity.animations.add(new Animation<>(0.05f, expRegion[0]));
        animationOfEntity.framesOfAnimation = 5;
        animationOfEntity.animationScale = 2.0f;

    }




}
