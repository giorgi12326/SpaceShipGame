package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Lasers extends Bullet{
    public static float spawnSpeed = 0.2f;
    public static float timer = 0;

    public Lasers(float x,float y){
        spriteOfEntity.scale = 1f;

        spriteOfEntity.texture = new Texture("laser.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(x);
        spriteOfEntity.sprite.setY(y);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight() * spriteOfEntity.scale;

        hitboxOfEntity.pixmap = new Pixmap(Gdx.files.internal("laser.png"));
        hitboxOfEntity.hitboxWidth = 4f * spriteOfEntity.scale;
        hitboxOfEntity.hitboxHeight = 20f * spriteOfEntity.scale;


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

    public void update(){

    }

}
