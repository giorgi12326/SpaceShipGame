package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rocket extends Bullet {
    public static float spawnSpeed = 1f;
    public static float timer = 0;

    public Rocket(float x,float y){
        spriteOfEntity.scale = 3f;
        animationOfEntity.animationScale = 3f;

        spriteOfEntity.texture = new Texture("rocket.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(x);
        spriteOfEntity.sprite.setY(y);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight() * spriteOfEntity.scale;

        hitboxOfEntity.pixmap = hitboxOfEntity.scalePixmap( new Pixmap(Gdx.files.internal("rocket.png")),spriteOfEntity.scale,spriteOfEntity.scale);
        hitboxOfEntity.hitboxWidth = 7f * spriteOfEntity.scale;
        hitboxOfEntity.hitboxHeight = 19f * spriteOfEntity.scale;

        Texture expImage = new Texture("explode.png");

//        spriteOfEntity.width = 150f;
////        spriteOfEntity.height = 60f;

        TextureRegion[][] expRegion = TextureRegion.split(expImage,150,60);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.framesOfAnimation = 5;
        animationOfEntity.sizeFull.add(new Pair(150f*animationOfEntity.animationScale,60f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(0,0));
        animationOfEntity.hitbox.add(new Pair(60f*animationOfEntity.animationScale,60f*animationOfEntity.animationScale));


    }
}
