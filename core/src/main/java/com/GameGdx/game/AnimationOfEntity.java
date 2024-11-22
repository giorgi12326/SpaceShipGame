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
    public int shouldDisplayAnimation = -1;
    public int framesOfAnimation;
    public float animationScale = 1;


    public AnimationOfEntity(Entity entity) {
        this.entity = entity;
    }

    public void drawAnimation(SpriteBatch batch,int index){
        batch.draw(animations.get(shouldDisplayAnimation).getKeyFrame(animationTimer), entity.sprite.sprite.getX() - entity.sprite.width * entity.sprite.scale * animationScale /2,
            entity.sprite.sprite.getY() - entity.sprite.height * entity.sprite.scale * animationScale /2, entity.sprite.width * entity.sprite.scale * animationScale, entity.sprite.height * entity.sprite.scale * animationScale);
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
