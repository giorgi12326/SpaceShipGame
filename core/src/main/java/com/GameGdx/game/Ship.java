package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Ship extends Entity{

    public static float moveSpeed = 200f;
    Ship(){
        scale = 3.0f;
        texture = new Texture("plane.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(Gdx.graphics.getWidth()/2f);
        sprite.setY(200);
        rectangle = new Rectangle();

        width = sprite.getWidth();
        height = sprite.getHeight();

        hitboxWidth = 17f;
        hitboxHeight = 16f;

        x = sprite.getX();
        y = sprite.getY();

//        Texture expImage = new Texture("rock_explode.png");
//        TextureRegion[][] expRegion = TextureRegion.split(expImage,53,38);
//        animation = new Animation<>(0.1f,expRegion[0]);
//        framesOfAnimation = 3;
//        shouldDisplayAnimation= false;


    }


}
