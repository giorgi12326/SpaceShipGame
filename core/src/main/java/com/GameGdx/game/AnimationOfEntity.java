package com.GameGdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationOfEntity {
    private final Entity entity;
    public float animationTimer = 0f;
    public Animation<TextureRegion> animation;
    public boolean shouldDisplayAnimation;
    public int framesOfAnimation;
    public float animationScale = 1;


    public AnimationOfEntity(Entity entity) {
        this.entity = entity;
    }
    public void drawAnimation(SpriteBatch batch){
        batch.draw(animation.getKeyFrame(animationTimer), entity.sprite.sprite.getX() - entity.sprite.width * entity.sprite.scale * animationScale /2,
            entity.sprite.sprite.getY() - entity.sprite.height * entity.sprite.scale * animationScale /2, entity.sprite.width * entity.sprite.scale * animationScale, entity.sprite.height * entity.sprite.scale * animationScale);
    }

}
