package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Ship extends Entity{

    public static float moveSpeed = 100f;

    Ship(){
        spriteOfEntity.scale = 2.0f;
        animationOfEntity.animationScale = 4.0f;

        spriteOfEntity.texture = new Texture("plane.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);

        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(Gdx.graphics.getWidth()/2f);
        spriteOfEntity.sprite.setY(200);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight()  * spriteOfEntity.scale;

        hitboxOfEntity.hitboxWidth = 29f * spriteOfEntity.scale;
        hitboxOfEntity.hitboxHeight = 29f * spriteOfEntity.scale;
        hitboxOfEntity.pixmap = new Pixmap(Gdx.files.internal("plane.png"));

        TextureRegion[][] anim1 = TextureRegion.split(new Texture("PhaseLeft.png"),16,16);
        animationOfEntity.animations.add(new Animation<>(0.03f, anim1[0]));
        animationOfEntity.sizeFull.add(new Pair(16f*animationOfEntity.animationScale,16f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(40,0));
        animationOfEntity.framesOfAnimation = 5;

        TextureRegion[][] anim2 = TextureRegion.split(new Texture("PhaseRight.png"),16,16);
        animationOfEntity.animations.add(new Animation<>(0.03f, anim2[0]));
        animationOfEntity.sizeFull.add(new Pair(16f*animationOfEntity.animationScale,16f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(-40,0));
        animationOfEntity.framesOfAnimation = 5;

    }
}
