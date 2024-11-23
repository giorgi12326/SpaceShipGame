package com.GameGdx.game;

import com.badlogic.gdx.Gdx;

public class Bullet extends Entity{
    public void moveSprite(){
        if(animationOfEntity.shouldDisplayAnimation == -1)
            spriteOfEntity.sprite.translateY(1000f * Gdx.graphics.getDeltaTime());

    }
}
