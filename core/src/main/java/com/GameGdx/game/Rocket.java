package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Rocket extends Bullet {
    public static float spawnSpeed = 1f;
    public static float timer = 0;

    public Rocket(float x,float y){
        spriteOfEntity.scale = 1f;
        animationOfEntity.animationScale = 1f;

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


        hitboxOfEntity.animationHitbox.add(new ArrayList<>());


        int frameWidth = 150; // width of each frame
        int frameHeight = 60; // height of each frame
        int framesPerRow = 5; // number of frames per row in the spritesheet
        int totalFrames = 5; // total number of frames

        // Step 1: Get the full Pixmap from the texture
        expRegion[0][0].getTexture().getTextureData().prepare();

        Pixmap fullPixmap = new Pixmap(Gdx.files.internal("explode.png"));

        // Step 2: Iterate over each frame
        for (int i = 0; i < totalFrames; i++) {
            // Calculate the position of the frame in the spritesheet
            int xi = (i % framesPerRow) * frameWidth; // x position of the frame
            int yi = (i / framesPerRow) * frameHeight; // y position of the frame

            // Step 3: Create a new Pixmap for each frame
            Pixmap framePixmap = new Pixmap(frameWidth, frameHeight, Pixmap.Format.RGBA8888);

            // Step 4: Copy pixels from the full Pixmap into the frame Pixmap
            for (int j = 0; j < frameWidth; j++) {
                for (int k = 0; k < frameHeight; k++) {
                    // Get the pixel color from the full Pixmap
                    int color = fullPixmap.getPixel(xi + j, yi + k);
                    // Set the pixel in the new frame Pixmap
                    framePixmap.drawPixel(j, k, color);
                }
            }
            hitboxOfEntity.animationHitbox.get(0).add(hitboxOfEntity.scalePixmap(framePixmap,spriteOfEntity.scale,spriteOfEntity.scale));

        }
//        for (Pixmap pixmap: hitboxOfEntity.animationHitbox){
//            for (int i = 0; i < pixmap.getHeight(); i++) {
//                for (int j = 25; j <  pixmap.getWidth()-25; j++) {
//                    if((pixmap.getPixel(j,i) & 0x000000FF) > 0)
//                        System.out.print(1);
//                    else System.out.print(0);
//
//                }
//                System.out.println();
//
//            }
//            System.out.println("----------");
//        }
    }
}
