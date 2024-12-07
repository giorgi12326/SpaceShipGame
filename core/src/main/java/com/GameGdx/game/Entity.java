package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Entity {
    public final AnimationOfEntity animationOfEntity = new AnimationOfEntity(this);
    public final SpriteOfEntity spriteOfEntity = new SpriteOfEntity(this);
    public final HitboxOfEntity hitboxOfEntity = new HitboxOfEntity(this);

    public Random random = new Random();
    float delta = Gdx.graphics.getDeltaTime();

    public static float timer = 0f;
    public static float spawnSpeed;

    public void update(SpriteBatch batch){
        if(animationOfEntity.shouldDisplayAnimation >= 0){
            hitBoxDuringAnimation();//has to be above drawAnimation!
            drawDuringAnimation(batch, animationOfEntity.shouldDisplayAnimation);
            moveSpriteDuringAnimation();
        }
        else{
            drawNormally(batch);
            hitBoxNormally();
            moveSprite();
        }
    }

    public void moveSpriteDuringAnimation() {
    }

    public void drawDuringAnimation(SpriteBatch batch, int index){
        animationOfEntity.drawAnimation(batch,index);
    }

    public void hitBoxDuringAnimation(){
        hitboxOfEntity.removeRectangle();
    }
    public void drawNormally(SpriteBatch batch){
        spriteOfEntity.draw(batch);
    }
    public void hitBoxNormally(){
        hitboxOfEntity.setSpriteRectangle();
    }
    public void moveSprite(){

    }
    public void loop(){
        animationOfEntity.loop();
    }



}
