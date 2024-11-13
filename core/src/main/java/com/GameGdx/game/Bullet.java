package com.GameGdx.game;

import com.badlogic.gdx.Gdx;

public class Bullet extends Entity{
    public void move(){
        sprite.translateY(1000f * Gdx.graphics.getDeltaTime());

    }
}
