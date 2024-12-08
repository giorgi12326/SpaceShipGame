package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends Entity{
    public static float moveSpeed = 100f;

    public boolean isItHit = false;
    public float damagedTimer = 1f;

    public int hearts = 3;

    public Ship(){
        spriteOfEntity.scale = 2.0f;
        animationOfEntity.animationScale = 4.0f;

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

        TextureRegion[][] anim1 = TextureRegion.split(new Texture("PhaseLeft.png"),16,16);
        animationOfEntity.animations.add(new Animation<>(0.03f, anim1[0]));
        animationOfEntity.sizeFull.add(new Pair(16f*animationOfEntity.animationScale,16f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(40,0));
        animationOfEntity.framesOfAnimation.add(5);

        TextureRegion[][] anim2 = TextureRegion.split(new Texture("PhaseRight.png"),16,16);
        animationOfEntity.animations.add(new Animation<>(0.03f, anim2[0]));
        animationOfEntity.sizeFull.add(new Pair(16f*animationOfEntity.animationScale,16f*animationOfEntity.animationScale));
        animationOfEntity.offset.add(new Pair(-40,0));
        animationOfEntity.framesOfAnimation.add(5);

    }

    @Override
    public void drawNormally(SpriteBatch batch) {
        if(isItHit) {
            if (((int)(damagedTimer/0.2f))%2 == 1)
                super.drawNormally(batch);
        }
        else
            super.drawNormally(batch);
    }

    @Override
    public void loop() {
        hitUpdate();

    }

    private void hitUpdate() {
        if(isItHit){
            if(damagedTimer == 1f){
                uponHit();
            }
            damagedTimer -= Gdx.graphics.getDeltaTime();
            if(damagedTimer <= 0){
                System.out.println("out");
                damagedTimer = 1f;
                isItHit = false;
            }
        }
    }

    private void uponHit() {
        hearts--;
        if(hearts == 0)
            System.exit(1);
    }

}
