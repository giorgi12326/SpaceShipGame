package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Rock extends Enemy{

    public static float spawnSpeed = 0.3f;
    public static float timer = 0;
    public static float moveSpeed = 400f;


    Rock(){
        spriteOfEntity.scale = 4.0f;

        spriteOfEntity.texture = new Texture("rock.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth();
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight();

        spriteOfEntity.sprite.setX(random.nextFloat(0, Gdx.graphics.getWidth() - spriteOfEntity.width * spriteOfEntity.scale));
        spriteOfEntity.sprite.setY(Gdx.graphics.getHeight());


        hitboxOfEntity.hitboxWidth = 16f;
        hitboxOfEntity.hitboxHeight = 13f;

        hitboxOfEntity.pixmap = new Pixmap(Gdx.files.internal("rock.png"));



        Texture expImage = new Texture("rock_explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,53,38);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.framesOfAnimation = 3;


    }




    @Override
    public void moveSprite(){
        spriteOfEntity.sprite.translateY(-moveSpeed*Gdx.graphics.getDeltaTime());

    }






}
