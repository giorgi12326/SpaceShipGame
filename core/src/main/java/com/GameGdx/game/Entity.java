package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Entity {
    public Random random = new Random();


    float scale;
    public float width;
    public float height;


    public static float timer = 0f;
    public static float spawnSpeed;

    Texture texture;
    Sprite sprite ;
    Rectangle rectangle;

    public float animationTimer = 0f;
    public Animation<TextureRegion> animation;
    public volatile boolean shouldDisplayAnimation;

    public void update(SpriteBatch batch){
        if(shouldDisplayAnimation) {
            if (animation.getKeyFrameIndex(animationTimer) == 2) {
                shouldDisplayAnimation = false;
            }
            animationTimer += Gdx.graphics.getDeltaTime();
            animate(batch);
        }
        else{
            rectangle.set(sprite.getX()+ sprite.getWidth()*(1-sprite.getScaleX())/2 ,
                sprite.getY() + sprite.getHeight()*(1-sprite.getScaleY())/2,sprite.getWidth()*scale,sprite.getHeight()* scale);
            sprite.draw(batch);
        }
    }
    public void animate(SpriteBatch batch){

        batch.draw(animation.getKeyFrame(animationTimer), sprite.getX() - 50 + sprite.getWidth() * (1 - sprite.getScaleX()) / 2,
            sprite.getY() - 19 + sprite.getHeight() * (1 - sprite.getScaleY()) / 2, 179, 114);

    }

    public void triggerAnimation(){
        shouldDisplayAnimation = true;
    }

}
