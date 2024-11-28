package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rock extends Enemy{

    public static float spawnSpeed = 0.4f;
    public static float timer = 0;
    public static float moveSpeed = 400f;//


    Rock(){
        spriteOfEntity.scale = 4.0f;
        animationOfEntity.animationScale = 4.0f;

        spriteOfEntity.texture = new Texture("rock.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);

        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight()* spriteOfEntity.scale;


        spriteOfEntity.sprite.setX(random.nextFloat(spriteOfEntity.width/2f - spriteOfEntity.sprite.getWidth()/2f,Gdx.graphics.getWidth() + spriteOfEntity.sprite.getWidth()/2f - spriteOfEntity.width/2f));//
        spriteOfEntity.sprite.setY(Gdx.graphics.getHeight());


        hitboxOfEntity.hitboxWidth = 16f * spriteOfEntity.scale;
        hitboxOfEntity.hitboxHeight = 13f * spriteOfEntity.scale;
        hitboxOfEntity.pixmap = hitboxOfEntity.scalePixmap( new Pixmap(Gdx.files.internal("rock.png")),spriteOfEntity.scale,spriteOfEntity.scale);
        Texture expImage = new Texture("rock_explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,53,38);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.sizeFull.add(new Pair(53f*animationOfEntity.animationScale,38f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(0,0));

        animationOfEntity.framesOfAnimation = 3;


    }




    @Override
    public void moveSprite(){
        spriteOfEntity.sprite.translateY(-moveSpeed*Gdx.graphics.getDeltaTime());

    }






}
