package com.GameGdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteOfEntity {
    Texture texture;
    Sprite sprite;
    float scale;
    public float width;
    public float height;

    public SpriteOfEntity(Entity entity) {
    }
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
}
