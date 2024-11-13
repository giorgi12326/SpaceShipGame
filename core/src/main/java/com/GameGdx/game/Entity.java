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

    public float hitboxWidth;
    public float hitboxHeight;

    float x;
    float y;

    public static float timer = 0f;
    public static float spawnSpeed;

    Texture texture;
    Sprite sprite ;
    Rectangle rectangle;

    public float animationTimer = 0f;
    public Animation<TextureRegion> animation;
    public boolean shouldDisplayAnimation;
    public int framesOfAnimation;
    public float animationScale = 1;

    public void update(SpriteBatch batch){

        if(shouldDisplayAnimation){

            batch.draw(animation.getKeyFrame(animationTimer), sprite.getX() - width*scale*animationScale/2,
                sprite.getY() - height*scale*animationScale/2,width*scale*animationScale, height*scale*animationScale);
            animationTimer += Gdx.graphics.getDeltaTime();
            if((this instanceof Explosion)) {
                setRectangle();
            }
            else
                rectangle.set(-1, -1, 0, 0);
            if (animation.getKeyFrameIndex(animationTimer) == framesOfAnimation-1) {
                animationTimer = 0;
                shouldDisplayAnimation = false;
            }
        }
        else{
            sprite.draw(batch);
            setRectangle();
            move();

        }
    }

    private void setRectangle() {
        rectangle.set(sprite.getX() + width / 2 -hitboxWidth*scale/2,
            sprite.getY() + height/ 2 -hitboxHeight*scale/2,
            hitboxWidth*scale, hitboxHeight*scale);
    }

    public void move(){

    }



}
