package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends Entity{

    public static float moveSpeed = 200f;

    Ship(){
        spriteOfEntity.scale = 3.0f;
        spriteOfEntity.texture = new Texture("plane.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(Gdx.graphics.getWidth()/2f);
        spriteOfEntity.sprite.setY(200);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth();
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight();

        hitboxOfEntity.hitboxWidth = 17f;
        hitboxOfEntity.hitboxHeight = 16f;

        Texture expImage = new Texture("PhaseLeft.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,16,16);
        animationOfEntity.animations.add(new Animation<>(0.05f, expRegion[0]));
        animationOfEntity.size.add(new Pair(16,16));
        animationOfEntity.framesOfAnimation = 5;
        animationOfEntity.animationScale = 2.0f;

    }




}
