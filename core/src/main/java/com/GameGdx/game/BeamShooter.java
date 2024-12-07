package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class BeamShooter extends Enemy{
    public static float spawnSpeed = 12f;
    public static float timer = 0;
    public static float moveSpeed = 100f;
    public static float countdown = 14f;

    Ship ship;

    public BeamShooter(Ship ship){
        this.ship = ship;

        spriteOfEntity.scale = 5.0f;
        animationOfEntity.animationScale = 2f;

        spriteOfEntity.texture = new Texture("plane.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);

        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(300);
        spriteOfEntity.sprite.setY(600);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight()* spriteOfEntity.scale;

        hitboxOfEntity.hitboxWidth = 18f * spriteOfEntity.scale;
        hitboxOfEntity.hitboxHeight = 16f * spriteOfEntity.scale;
        hitboxOfEntity.pixmap = new Pixmap(Gdx.files.internal("ufo.png"));


        Texture expImage = new Texture("flame.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,32,640);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.sizeFull.add(new Pair(32*animationOfEntity.animationScale,640*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(0,-640));
        animationOfEntity.hitbox.add(new Pair(32*animationOfEntity.animationScale,640*animationOfEntity.animationScale));
        animationOfEntity.looping.add(false);
        animationOfEntity.framesOfAnimation.add(3);
        animationOfEntity.shouldDisplayAnimation = -1;
        animationOfEntity.numberOfSequentialAnimation = 2;

        Texture expImage2 = new Texture("flameLinkLoop.png");
        TextureRegion[][] expRegion2 = TextureRegion.split(expImage2,32,640);
        Animation<TextureRegion> textureRegionAnimation = new Animation<>(0.1f, expRegion2[0]);
        textureRegionAnimation.setPlayMode(Animation.PlayMode.LOOP);
        animationOfEntity.animations.add(textureRegionAnimation);
        animationOfEntity.sizeFull.add(new Pair(32*animationOfEntity.animationScale,640*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(0,-640));
        animationOfEntity.hitbox.add(new Pair(32*animationOfEntity.animationScale,640*animationOfEntity.animationScale));
        animationOfEntity.looping.add(true);
        animationOfEntity.framesOfAnimation.add(4);

    }
    @Override
    public void moveSprite(){
        if(animationOfEntity.shouldDisplayAnimation == -1)
            spriteOfEntity.sprite.translateY(moveSpeed*Gdx.graphics.getDeltaTime()*1.5f);
            else {
            if (ship.spriteOfEntity.sprite.getX() > spriteOfEntity.sprite.getX() + 10)
                spriteOfEntity.sprite.translateX(moveSpeed * Gdx.graphics.getDeltaTime() * 2);
            else if (ship.spriteOfEntity.sprite.getX() < spriteOfEntity.sprite.getX() - 10)
                spriteOfEntity.sprite.translateX(-moveSpeed * Gdx.graphics.getDeltaTime() * 2);
        }

    }

    @Override
    public void moveSpriteDuringAnimation() {
        if(ship.spriteOfEntity.sprite.getX()  > spriteOfEntity.sprite.getX()+10)
            spriteOfEntity.sprite.translateX(moveSpeed*Gdx.graphics.getDeltaTime());
        else if(ship.spriteOfEntity.sprite.getX()  < spriteOfEntity.sprite.getX()-10)
            spriteOfEntity.sprite.translateX(-moveSpeed*Gdx.graphics.getDeltaTime());
    }
    public void drawDuringAnimation(SpriteBatch batch, int index){
        animationOfEntity.drawAnimation(batch,index);
        spriteOfEntity.draw(batch);

    }

    @Override
    public void hitBoxNormally() {
        hitboxOfEntity.removeRectangle();
    }
}
