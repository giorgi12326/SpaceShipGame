package com.GameGdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;

public class HitboxOfEntity {
    private final Entity entity;
    public float hitboxWidth;
    public float hitboxHeight;
    Rectangle rectangle = new Rectangle();
    Pixmap pixmap;


    public HitboxOfEntity(Entity entity) {
        this.entity = entity;
    }

    public void setRectangle() {
        rectangle.set(entity.spriteOfEntity.sprite.getX() - entity.hitboxOfEntity.hitboxWidth/2f + entity.spriteOfEntity.sprite.getWidth()/2f,
            entity.spriteOfEntity.sprite.getY() - entity.hitboxOfEntity.hitboxHeight/2f  + entity.spriteOfEntity.sprite.getHeight()/2f ,
                hitboxWidth, hitboxHeight);
    }
    public void setRectangle(float x,float y,float width,float height,float scale) {
        rectangle.set(entity.spriteOfEntity.sprite.getX()  - height * scale / 2, y - width * scale / 2,
                width * scale, height * scale);
    }

    void removeRectangle() {
        rectangle.set(-1, -1, 0, 0);

    }




}
