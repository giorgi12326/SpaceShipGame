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
    public List<Pair> size = new ArrayList<>();
    public int shouldDisplayAnimation = -1;
    public int framesOfAnimation;
    public float animationScale = 1;

    public float animationWidth;
    public float animationHeight;



    public AnimationOfEntity(Entity entity) {
        this.entity = entity;
    }

    public void drawAnimation(SpriteBatch batch,int index){
//        System.out.println(entity.spriteOfEntity.width/2);
        batch.draw(animations.get(shouldDisplayAnimation).getKeyFrame(animationTimer),
            entity.spriteOfEntity.sprite.getX() - animationWidth/2f,
            entity.spriteOfEntity.sprite.getY() - animationHeight/2f
            , entity.animationOfEntity.size.get(index).x() * animationScale, entity.animationOfEntity.size.get(index).y() * animationScale);
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
