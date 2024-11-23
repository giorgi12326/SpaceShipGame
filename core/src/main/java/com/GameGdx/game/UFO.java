package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UFO extends Enemy {
    public static float spawnSpeed = 4f;
    public static float timer = 0;
    public static float moveSpeed = 100f;

    Ship ship;

    UFO(Ship ship){
        this.ship = ship;

        spriteOfEntity.scale = 5.0f;
        animationOfEntity.animationScale = 6f;

        spriteOfEntity.texture = new Texture("ufo.png");
        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
        spriteOfEntity.sprite.setX(Gdx.graphics.getWidth());
        spriteOfEntity.sprite.setY(random.nextFloat(Math.max(200,ship.spriteOfEntity.sprite.getY())) + 400);

        spriteOfEntity.width = spriteOfEntity.sprite.getWidth() * spriteOfEntity.scale;
        spriteOfEntity.height = spriteOfEntity.sprite.getHeight() * spriteOfEntity.scale;

        hitboxOfEntity.hitboxWidth = 18f;
        hitboxOfEntity.hitboxHeight = 16f;

        Texture expImage = new Texture("OmegaBolt.png");
        TextureRegion[][] expRegion = TextureRegion.split(expImage,100,100);
        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
        animationOfEntity.size.add(new Pair(100,100));
        animationOfEntity.framesOfAnimation = 8;
        animationOfEntity.animationWidth = 100* animationOfEntity.animationScale;
        animationOfEntity.animationHeight = 100* animationOfEntity.animationScale;

    }
    @Override
    public void moveSprite(){
        if(ship.spriteOfEntity.sprite.getX()  > spriteOfEntity.sprite.getX()+10)
            spriteOfEntity.sprite.translateX(moveSpeed*Gdx.graphics.getDeltaTime()*2);
        else if(ship.spriteOfEntity.sprite.getX()  < spriteOfEntity.sprite.getX()-10)
            spriteOfEntity.sprite.translateX(-moveSpeed*Gdx.graphics.getDeltaTime()*2);
    }

    @Override
    public void hitBoxDuringAnimation() {
        hitboxOfEntity.setRectangleAnimation();

    }

    @Override
    public void loop() {
        if(timer ==2f)
            animationOfEntity.triggerAnimation();
    }
}
