package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends Entity{

    public static float moveSpeed = 100f;

    Ship(){
        spriteOfEntity.scale = 3.0f;
        animationOfEntity.animationScale = 3.0f;

        spriteOfEntity.texture = new Texture("plane.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);

        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(Gdx.graphics.getWidth()/2f);
        spriteOfEntity.sprite.setY(200);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight()  * spriteOfEntity.scale;

        hitboxOfEntity.hitboxWidth = 29f * spriteOfEntity.scale;
        hitboxOfEntity.hitboxHeight = 29f * spriteOfEntity.scale;
        hitboxOfEntity.pixmap = hitboxOfEntity.scalePixmap( new Pixmap(Gdx.files.internal("plane.png")),spriteOfEntity.scale,spriteOfEntity.scale);



        Texture expImage = new Texture("PhaseLeft.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,16,16);
        animationOfEntity.animations.add(new Animation<>(0.05f, expRegion[0]));
        animationOfEntity.sizeFull.add(new Pair(16f*animationOfEntity.animationScale,16f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(0,0));


        animationOfEntity.framesOfAnimation = 5;
        System.out.println(hitboxOfEntity.pixmap.getPixel(24,26));
//
//        for (int i = 0; i < hitboxOfEntity.pixmap.getHeight(); i++) {
//            for (int j = 0; j <  hitboxOfEntity.pixmap.getWidth(); j++) {
//                if(hitboxOfEntity.pixmap.getPixel(j,i) != 0)
//                    System.out.print( 1);
//                else
//                    System.out.print( 0);
//
//
//            }
//            System.out.println();
//
//        }

    }




}
