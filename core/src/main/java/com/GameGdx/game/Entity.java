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
    public boolean shouldDisplayAnimation;
    public int framesOfAnimation;




    public void update(SpriteBatch batch){
        if(shouldDisplayAnimation){
            animationTimer += Gdx.graphics.getDeltaTime();
            batch.draw(animation.getKeyFrame(animationTimer), sprite.getX() - width*scale + width/2,
                sprite.getY() - width*scale + width/2,width*scale*2, height*scale*2);
            if((this instanceof Explosion)) {
                rectangle.set(sprite.getX() - width * sprite.getScaleX() / 2 + width,
                    sprite.getY() - height * sprite.getScaleY() / 2 + height,
                    height * scale, height * scale);
            }
            if (animation.getKeyFrameIndex(animationTimer) == framesOfAnimation-1) {
                animationTimer = 0;
                shouldDisplayAnimation = false;
            }
        }
        else{
            sprite.draw(batch);
            rectangle.set(sprite.getX() - width * sprite.getScaleX() / 2 + width,
                sprite.getY() - height * sprite.getScaleY() / 2 + height,
                height * scale, height * scale);
            move();

        }
    }
    public void move(){

    }


    public void triggerAnimation(){
        shouldDisplayAnimation = true;
    }

}
