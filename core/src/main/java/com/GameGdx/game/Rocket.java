package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rocket extends Bullet {
    public static float spawnSpeed = 1f;
    public static float timer = 0;

    public Rocket(float x,float y){
        spriteOfEntity.scale = 3f;
        animationOfEntity.animationScale = 8f;

        spriteOfEntity.texture = new Texture("rocket.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(x);
        spriteOfEntity.sprite.setY(y);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight() * spriteOfEntity.scale;

        Texture expImage = new Texture("explode.png");



        TextureRegion[][] expRegion = TextureRegion.split(expImage,150,60);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.size.add(new Pair(150,60));
        animationOfEntity.shouldDisplayAnimation = -1;
        animationOfEntity.framesOfAnimation = 5;
        animationOfEntity.animationWidth = 150 * animationOfEntity.animationScale;
        animationOfEntity.animationHeight = 60 * animationOfEntity.animationScale;




    }

//    @Override
//    public void hitBoxDuringAnimation() {
//        hitboxOfEntity.setRectangle();
//        System.out.println(hitboxOfEntity.rectangle.getWidth() + " " + hitboxOfEntity.rectangle.getHeight());
//    }
}
