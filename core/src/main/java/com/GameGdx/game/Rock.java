package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Rock extends Enemy{

    public static float spawnSpeed = 0.25f;
    public static float timer = 0;

    Rock(){
        scale = 4.0f;

        texture = new Texture("rock.png");
        sprite = new Sprite(texture);
        sprite.setScale(scale);
        sprite.setX(random.nextFloat((scale - 1f)/2, Gdx.graphics.getWidth() - width - width*scale/2));
        sprite.setY(Gdx.graphics.getHeight());
        rectangle = new Rectangle();

        width = sprite.getWidth() * scale;
        height = sprite.getHeight() * scale;

        Texture expImage = new Texture("rock_explode.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,53,38);
        animation = new Animation<>(0.1f,expRegion[0]);





    }
//
////    x = random.nextFloat(width*(scale - 1)/2);
//    public static Texture texture = new Texture("rock.png");
//    public static TextureRegion[][] textureRegions = TextureRegion.split(texture,53,38);
////    Animation<TextureRegion> animation = new Animation<>(0.1f,textureRegions[0]);
//    public Sprite sprite = new Sprite(texture);
//    Rock(int x,int y,int scale){


}
