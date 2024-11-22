package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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


        hitboxOfEntity.hitboxWidth = 16f;
        hitboxOfEntity.hitboxHeight = 13f;



        Texture expImage = new Texture("rock_explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,53,38);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.framesOfAnimation = 3;


    }




    @Override
    public void moveSprite(){
        sprite.sprite.translateY(-moveSpeed*Gdx.graphics.getDeltaTime());

    }






}
