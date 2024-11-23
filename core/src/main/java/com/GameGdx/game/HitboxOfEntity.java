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

    public void setRectangleSprite() {
        rectangle.set(entity.spriteOfEntity.sprite.getX() + entity.spriteOfEntity.width/entity.spriteOfEntity.scale / 2 - hitboxHeight * entity.spriteOfEntity.scale / 2,
            entity.spriteOfEntity.sprite.getY() + entity.spriteOfEntity.height/entity.spriteOfEntity.scale / 2 - hitboxHeight * entity.spriteOfEntity.scale / 2,
                hitboxWidth* entity.spriteOfEntity.scale, hitboxHeight * entity.spriteOfEntity.scale);
    }
    public void setRectangleAnimation(){
        setRectangleAnimation(0);
    }
    public void setRectangleAnimation(int index){
        rectangle.set(entity.spriteOfEntity.sprite.getX() + entity.animationOfEntity.animationWidth/entity.animationOfEntity.animationScale / 2 - hitboxHeight * entity.animationOfEntity.animationScale/ 2,
             entity.spriteOfEntity.sprite.getY() + entity.animationOfEntity.animationHeight/entity.animationOfEntity.animationScale / 2 - hitboxHeight * entity.animationOfEntity.animationScale / 2,
            hitboxWidth* entity.animationOfEntity.animationScale, hitboxHeight * entity.animationOfEntity.animationScale);
    }

    public void setRectangleSprite(float x,float y,float width,float height,float scale) {
        rectangle.set(entity.spriteOfEntity.sprite.getX()  - height / 2, y - width / 2,
                width , height );
    }


    void removeRectangle() {
        rectangle.set(-1, -1, 0, 0);

    }




}
