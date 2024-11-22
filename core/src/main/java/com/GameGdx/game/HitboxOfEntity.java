package com.GameGdx.game;

import com.badlogic.gdx.math.Rectangle;

public class HitboxOfEntity {
    private final Entity entity;
    public float hitboxWidth;
    public float hitboxHeight;
    Rectangle rectangle = new Rectangle();


    public HitboxOfEntity(Entity entity) {
        this.entity = entity;
    }

    public void setRectangle() {
        rectangle.set(entity.sprite.sprite.getX() + entity.sprite.width / 2 - hitboxHeight * entity.sprite.scale / 2, entity.sprite.sprite.getY() + entity.sprite.height / 2 - hitboxHeight * entity.sprite.scale / 2,
                hitboxWidth* entity.sprite.scale, hitboxHeight * entity.sprite.scale);
    }

    void removeRectangle() {
        rectangle.set(-1, -1, 0, 0);

    }




}
