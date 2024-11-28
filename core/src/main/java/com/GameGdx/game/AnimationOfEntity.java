package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public class AnimationOfEntity {
    private final Entity entity;
    public float animationTimer = 0f;
    public List<Animation<TextureRegion>> animations = new ArrayList<>();
    public List<Pair> sizeFull = new ArrayList<>();
    public List<Pair> offset = new ArrayList<>();
    public List<Pair> hitbox = new ArrayList<>();
    public int shouldDisplayAnimation = -1;
    public int framesOfAnimation;
    public static float animationScale = 1;



    public AnimationOfEntity(Entity entity) {
        this.entity = entity;
    }

    public void drawAnimation(SpriteBatch batch,int index){
        Pair getSizeFull = sizeFull.get(index);
        Pair getOffset = offset.get(index);
        batch.draw(animations.get(shouldDisplayAnimation).getKeyFrame(animationTimer),
            entity.spriteOfEntity.sprite.getX() - getSizeFull.x()/2f + entity.spriteOfEntity.sprite.getWidth()/2f + getOffset.x(),
            entity.spriteOfEntity.sprite.getY() - getSizeFull.y()/2f + entity.spriteOfEntity.sprite.getHeight()/2f + getOffset.y(),
            getSizeFull.x(), getSizeFull.y());
        updateAnimationTimer();
        checkIfAnimationShouldEnd();
    }

    private void checkIfAnimationShouldEnd() {
        if (animations.get(shouldDisplayAnimation).getKeyFrameIndex(animationTimer) == framesOfAnimation -1) {
            animationTimer = 0;
            shouldDisplayAnimation = -1;
        }
    }


    public void triggerAnimation(){
        shouldDisplayAnimation = 0;
    }
    public void triggerAnimation(int index){
        shouldDisplayAnimation = index;
    }
    public void updateAnimationTimer() {
        animationTimer = animationTimer + Gdx.graphics.getDeltaTime();
    }

    public void loop() {

    }
}
