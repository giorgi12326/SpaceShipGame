package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Rock extends Enemy{

    public static float spawnSpeed = 1.2f;
    public static float timer = 0;
    public static float moveSpeed = 400f;


    Rock(){
        scale = 6.0f;
        texture = new Texture("rock.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(random.nextFloat((scale - 1f)/2, Gdx.graphics.getWidth() - width - width*scale/2));
        sprite.setY(Gdx.graphics.getHeight());
        rectangle = new Rectangle();

        width = sprite.getWidth();
        height = sprite.getHeight();

        Texture expImage = new Texture("rock_explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,53,38);
        animation = new Animation<>(0.1f,expRegion[0]);
        framesOfAnimation = 3;
        shouldDisplayAnimation= false;


    }
    @Override
    public void move(){
        sprite.translateY(-moveSpeed*Gdx.graphics.getDeltaTime());

    }

    //    entity.animationTimer += Gdx.graphics.getDeltaTime();
//    Animation<TextureRegion> current = entity.animation;
//                batch.draw(current.getKeyFrame(entity.animationTimer),entity.sprite.getX() - Explosion.widthOfRegion*entity.sprite.getScaleX()/2f ,
//        entity.sprite.getY() - Explosion.heightOfRegion*entity.sprite.getScaleY()/2f, Explosion.widthOfRegion*entity.sprite.getScaleX(), Explosion.heightOfRegion*entity.sprite.getScaleY());
//
//                entity.rectangle.set(entity.sprite.getX() - Explosion.heightOfRegion*entity.sprite.getScaleX()/2 ,
//        entity.sprite.getY() - Explosion.heightOfRegion*entity.sprite.getScaleY()/2 +20,entity.sprite.getHeight()*entity.sprite.getScaleX(),entity.sprite.getHeight()*entity.sprite.getScaleY());
//                if (current.getKeyFrameIndex(entity.animationTimer) == 3) {
//        explosionList.remove(entity);
//        pendingAnimations.remove(i);






}
