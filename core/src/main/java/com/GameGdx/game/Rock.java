package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Rock extends Enemy{

    public static float spawnSpeed = 0.3f;
    public static float timer = 0;
    public static float moveSpeed = 400f;


    Rock(){
        sprite.scale = 4.0f;

        sprite.texture = new Texture("rock.png");
        sprite.sprite = new Sprite(sprite.texture);
        sprite.sprite.setScale(sprite.scale);

        sprite.width = sprite.sprite.getWidth();
        sprite.height = sprite.sprite.getHeight();

        sprite.sprite.setX(random.nextFloat(0, Gdx.graphics.getWidth() - sprite.width * sprite.scale));
        sprite.sprite.setY(Gdx.graphics.getHeight());
        rectangle = new Rectangle();

        hitboxOfEntity.hitboxWidth = 16f;
        hitboxOfEntity.hitboxHeight = 13f;



        Texture expImage = new Texture("rock_explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,53,38);
        animationOfEntity.animation = new Animation<>(0.1f, expRegion[0]);
        animationOfEntity.framesOfAnimation = 3;
        animationOfEntity.shouldDisplayAnimation = false;


    }
    @Override
    public void move(){
        sprite.sprite.translateY(-moveSpeed*Gdx.graphics.getDeltaTime());

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
