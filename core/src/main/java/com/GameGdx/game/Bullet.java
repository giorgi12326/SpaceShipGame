package com.GameGdx.game;

import com.badlogic.gdx.Gdx;

public class Bullet extends Entity{
    public void moveSprite(){
        spriteOfEntity.sprite.translateY(1000f * Gdx.graphics.getDeltaTime());

    }
}
