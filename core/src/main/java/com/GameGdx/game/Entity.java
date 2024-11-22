package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Entity {
    public final AnimationOfEntity animationOfEntity = new AnimationOfEntity(this);
    public final SpriteOfEntity sprite = new SpriteOfEntity(this);
    public final HitboxOfEntity hitboxOfEntity = new HitboxOfEntity(this);

    public Random random = new Random();
    float delta = Gdx.graphics.getDeltaTime();


    public static float timer = 0f;
    public static float spawnSpeed;


    Rectangle rectangle;

    public void update(SpriteBatch batch){
        if(animationOfEntity.shouldDisplayAnimation){
            animationOfEntity.drawAnimation(batch);
            animationOfEntity.animationTimer = animationOfEntity.animationTimer + Gdx.graphics.getDeltaTime();
            if((this instanceof Explosion))
                setBatchRectangle();
            else
                setAnimationRectangle();
            if (animationOfEntity.animation.getKeyFrameIndex(animationOfEntity.animationTimer) == animationOfEntity.framesOfAnimation -1) {
                animationOfEntity.animationTimer = 0;
                animationOfEntity.shouldDisplayAnimation = false;
            }
        }
        else{
            sprite.sprite.draw(batch);
            setBatchRectangle();
            move();

        }
    }
    public void setBatchRectangle() {
        rectangle.set(sprite.sprite.getX() + sprite.width / 2 - hitboxOfEntity.hitboxHeight * sprite.scale / 2, sprite.sprite.getY() + sprite.height / 2 - hitboxOfEntity.hitboxHeight * sprite.scale / 2,
                hitboxOfEntity.hitboxWidth* sprite.scale, hitboxOfEntity.hitboxHeight * sprite.scale);
    }

    void setAnimationRectangle() {
        rectangle.set(-1, -1, 0, 0);

    }

    public void triggerAnimation(){
        animationOfEntity.shouldDisplayAnimation = true;
    }

    public void move(){

    }
    public void loop(){

    }



}
