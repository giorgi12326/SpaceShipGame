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
        animationOfEntity.animationScale = 2.0f;

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



        Texture expImage = new Texture("PhaseLeft.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,16,16);
        animationOfEntity.animations.add(new Animation<>(0.05f, expRegion[0]));
        animationOfEntity.sizeFull.add(new Pair(16f*animationOfEntity.animationScale,16f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(0,0));


        animationOfEntity.framesOfAnimation = 5;
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
        hitboxOfEntity.animationHitbox.add(new ArrayList<>());








        int frameWidth = 16; // width of each frame
        int frameHeight = 16; // height of each frame
        int framesPerRow = 5; // number of frames per row in the spritesheet
        int totalFrames = 5; // total number of frames

    // Step 1: Get the full Pixmap from the texture
        expRegion[0][0].getTexture().getTextureData().prepare();

        Pixmap fullPixmap = expRegion[0][0].getTexture().getTextureData().consumePixmap();

    // Step 2: Iterate over each frame
        for (int i = 0; i < totalFrames; i++) {
            // Calculate the position of the frame in the spritesheet
            int x = (i % framesPerRow) * frameWidth; // x position of the frame
            int y = (i / framesPerRow) * frameHeight; // y position of the frame

            // Step 3: Create a new Pixmap for each frame
            Pixmap framePixmap = new Pixmap(frameWidth, frameHeight, Pixmap.Format.RGBA8888);

            // Step 4: Copy pixels from the full Pixmap into the frame Pixmap
            for (int j = 0; j < frameWidth; j++) {
                for (int k = 0; k < frameHeight; k++) {
                    // Get the pixel color from the full Pixmap
                    int color = fullPixmap.getPixel(x + j, y + k);
                    // Set the pixel in the new frame Pixmap
                    framePixmap.drawPixel(j, k, color);
                }
            }
            hitboxOfEntity.animationHitbox.get(0).add(framePixmap);

            // Now you have a Pixmap for each frame (framePixmap)
            // You can store these Pixmaps in an array or list for later use
        }
//        for (Pixmap pixmap: hitboxOfEntity.animationHitbox.get(0)){
//            for (int i = 0; i < pixmap.getHeight(); i++) {
//                for (int j = 0; j <  pixmap.getWidth(); j++) {
//                    if((pixmap.getPixel(j,i) & 0x000000FF) > 0)
//                        System.out.print( 1);
//                    else
//                        System.out.print( 0);
//
//                }
//                System.out.println();
//
//            }
//            System.out.println("----------");
//        }


    }
}
