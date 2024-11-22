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
    public static float moveSpeed = 100f;

    Ship ship;

    UFO(Ship ship){
        this.ship = ship;

        sprite.scale = 5.0f;
        animationOfEntity.animationScale = 6f;

        sprite.texture = new Texture("ufo.png");
        sprite.sprite = new Sprite(sprite.texture);
        sprite.sprite.setScale(sprite.scale);
        sprite.sprite.setX(Gdx.graphics.getWidth());
        sprite.sprite.setY(random.nextFloat(Math.max(200,ship.sprite.sprite.getY())) + 400);
        rectangle = new Rectangle();

        sprite.width = sprite.sprite.getWidth();
        sprite.height = sprite.sprite.getHeight();

        hitboxOfEntity.hitboxWidth = 18f;
        hitboxOfEntity.hitboxHeight = 16f;

        Texture expImage = new Texture("OmegaBolt.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,100,100);
        animationOfEntity.animation = new Animation<>(0.1f, expRegion[0]);
        animationOfEntity.framesOfAnimation = 8;
        animationOfEntity.shouldDisplayAnimation = false;

    }
    @Override
    public void move(){
        if(ship.sprite.sprite.getX()  > sprite.sprite.getX()+10)
            sprite.sprite.translateX(moveSpeed*Gdx.graphics.getDeltaTime()*2);
        else if(ship.sprite.sprite.getX()  < sprite.sprite.getX()-10)
            sprite.sprite.translateX(-moveSpeed*Gdx.graphics.getDeltaTime()*2);



    }

    @Override
    public void loop() {
        if(timer ==2f)
            triggerAnimation();
    }
}
