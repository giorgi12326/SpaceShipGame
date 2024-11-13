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

    Ship ship;

    UFO(Ship ship){
        this.ship = ship;

        scale = 5.0f;
        animationScale = 6f;

        texture = new Texture("ufo.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(Gdx.graphics.getWidth());
        sprite.setY(random.nextFloat(ship.sprite.getY()) + 400);
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
        System.out.println(ship.sprite.getX());
        if(ship.sprite.getX()  > sprite.getX()+ 3f)
            sprite.translateX(moveSpeed*Gdx.graphics.getDeltaTime());
        else if (ship.sprite.getX()  < sprite.getX() - 3f)
            sprite.translateX(-moveSpeed*Gdx.graphics.getDeltaTime());



    }
}
