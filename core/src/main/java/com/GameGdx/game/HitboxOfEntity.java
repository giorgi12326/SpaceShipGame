package com.GameGdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

import java.util.ArrayList;

public class HitboxOfEntity {
    private final Entity entity;
    public float hitboxWidth;
    public float hitboxHeight;
    Rectangle rectangle = new Rectangle();
    List<Pixmap> animationHitbox = new ArrayList<>();
    Pixmap pixmap;



    public HitboxOfEntity(Entity entity) {
        this.entity = entity;
    }

    public void setSpriteRectangle() {
        rectangle.set(entity.spriteOfEntity.sprite.getX() - entity.hitboxOfEntity.hitboxWidth/2f + entity.spriteOfEntity.sprite.getWidth()/2f,
            entity.spriteOfEntity.sprite.getY() - entity.hitboxOfEntity.hitboxHeight/2f  + entity.spriteOfEntity.sprite.getHeight()/2f ,
                hitboxWidth, hitboxHeight);
    }

    public void setAnimationRectangle(int index){
        Pair getHitbox = entity.animationOfEntity.hitbox.get(index);
        Pair getOffset = entity.animationOfEntity.offset.get(index);
        rectangle.set(entity.spriteOfEntity.sprite.getX() + entity.spriteOfEntity.sprite.getWidth()/2f - getHitbox.x()/2f + getOffset.x(),
            entity.spriteOfEntity.sprite.getY() + entity.spriteOfEntity.sprite.getHeight()/2f - getHitbox.y()/2f + getOffset.y(),
            getHitbox.x(),getHitbox.y());

    }

    void removeRectangle() {
        rectangle.set(-1, -1, 0, 0);

    }
    public boolean overlapsSpriteHitbox(Entity secondEntity){
        return doPixmapsOverlap(pixmap,
            entity.spriteOfEntity.sprite.getX() - entity.spriteOfEntity.width/2f + entity.spriteOfEntity.sprite.getWidth()/2f,
            entity.spriteOfEntity.sprite.getY() - entity.spriteOfEntity.height/2f  + entity.spriteOfEntity.sprite.getHeight()/2f,
            entity.spriteOfEntity.scale,
            secondEntity.hitboxOfEntity.pixmap,
            secondEntity.spriteOfEntity.sprite.getX() - secondEntity.spriteOfEntity.width/2f + secondEntity.spriteOfEntity.sprite.getWidth()/2f,
            secondEntity.spriteOfEntity.sprite.getY() - secondEntity.spriteOfEntity.height/2f  + secondEntity.spriteOfEntity.sprite.getHeight()/2f,
            secondEntity.spriteOfEntity.scale);
    }
    public boolean animationOverlapsSpriteHitbox(Entity secondEntity){
        return customDoPixmapsOverlap(
            entity.hitboxOfEntity.animationHitbox.get(entity.animationOfEntity.shouldDisplayAnimation),
            entity.spriteOfEntity.sprite.getX() - entity.animationOfEntity.sizeFull.get(entity.animationOfEntity.shouldDisplayAnimation).x()/2f
                + entity.spriteOfEntity.sprite.getWidth()/2f
                + entity.animationOfEntity.offset.get(entity.animationOfEntity.shouldDisplayAnimation).x(),
            entity.spriteOfEntity.sprite.getY() - entity.animationOfEntity.sizeFull.get(entity.animationOfEntity.shouldDisplayAnimation).y()/2f
                + entity.spriteOfEntity.sprite.getHeight()/2f
                + entity.animationOfEntity.offset.get(entity.animationOfEntity.shouldDisplayAnimation).y(),
            entity.animationOfEntity.animations.get(entity.animationOfEntity.shouldDisplayAnimation).getKeyFrameIndex(entity.animationOfEntity.animationTimer)*
                (int)entity.animationOfEntity.sizeFull.get(entity.animationOfEntity.shouldDisplayAnimation).x()/(int)entity.animationOfEntity.animationScale,
            (int)entity.animationOfEntity.sizeFull.get(entity.animationOfEntity.shouldDisplayAnimation).x()/(int)entity.animationOfEntity.animationScale,
            entity.animationOfEntity.animationScale,
            secondEntity.hitboxOfEntity.pixmap,
            secondEntity.spriteOfEntity.sprite.getX() - secondEntity.spriteOfEntity.width/2f + secondEntity.spriteOfEntity.sprite.getWidth()/2f,
            secondEntity.spriteOfEntity.sprite.getY() - secondEntity.spriteOfEntity.height/2f  + secondEntity.spriteOfEntity.sprite.getHeight()/2f,
            0,(int)secondEntity.spriteOfEntity.sprite.getWidth(),
            secondEntity.spriteOfEntity.scale
            );
    }

    private boolean customDoPixmapsOverlap(
        Pixmap pixmap1, float x1, float y1, int startPoint1, int sizeX1, float scale1,
        Pixmap pixmap2, float x2, float y2, int startPoint2, int sizeX2, float scale2) {

//
        // Calculate the bounds of the subregions in global coordinates
        float region1XStart = x1;
        float region1XEnd = x1 + sizeX1 * scale1;
        float region1YStart = y1;
        float region1YEnd = y1 + pixmap1.getHeight() * scale1;

        float region2XStart = x2;
        float region2XEnd = x2 + sizeX2 * scale2;
        float region2YStart = y2;
        float region2YEnd = y2 + pixmap2.getHeight() * scale2;


        // Determine the overlap region in global coordinates
        float overlapXStart = Math.max(region1XStart, region2XStart);
        float overlapYStart = Math.max(region1YStart, region2YStart);
        float overlapXEnd = Math.min(region1XEnd, region2XEnd);
        float overlapYEnd = Math.min(region1YEnd, region2YEnd);

        // If there is no overlap, return false
        if (overlapXStart >= overlapXEnd || overlapYStart >= overlapYEnd) {
            return false;
        }

        // Convert overlap region to integer bounds for iteration
        int startX = (int) Math.floor(overlapXStart);
        int startY = (int) Math.floor(overlapYStart);
        int endX = (int) Math.ceil(overlapXEnd);
        int endY = (int) Math.ceil(overlapYEnd);

        // Check pixels in the overlapping region
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                // Convert global coordinates to local Pixmap coordinates, accounting for scaling
                int pixmap1X = (int) ((x - x1) / scale1) + startPoint1;
                int pixmap1Y = pixmap1.getHeight() - 1 - (int) ((y - y1)/ scale1); // Flip Y for Pixmap 1


                int pixmap2X = (int) ((x - x2) / scale2) + startPoint2;
                int pixmap2Y = pixmap2.getHeight() - 1 - (int) ((y - y2)/ scale2); // Flip Y for Pixmap 1

                // Ensure indices are within the subregion bounds for each Pixmap
                if (pixmap1X >= startPoint1 && pixmap1X < startPoint1 + sizeX1 &&
                    pixmap1Y >= 0 && pixmap1Y < pixmap1.getHeight() &&
                    pixmap2X >= startPoint2 && pixmap2X < startPoint2 + sizeX2 &&
                    pixmap2Y >= 0 && pixmap2Y < pixmap2.getHeight()) {

                    // Get pixel colors
                    int color1 = pixmap1.getPixel(pixmap1X, pixmap1Y);
                    int color2 = pixmap2.getPixel(pixmap2X, pixmap2Y);

                    // Check if both pixels are non-transparent
                    if ((color1 & 0x000000FF) > 0 && (color2 & 0x000000FF) > 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean doPixmapsOverlap(Pixmap pixmap1, float x1, float y1, float scale1,
                                    Pixmap pixmap2, float x2, float y2, float scale2) {

        // Determine the overlap region in global coordinates
        float overlapXStart = Math.max(x1, x2);
        float overlapYStart = Math.max(y1, y2);
        float overlapXEnd = Math.min(x1 + pixmap1.getWidth() * scale1, x2 + pixmap2.getWidth() * scale2);
        float overlapYEnd = Math.min(y1 + pixmap1.getHeight() * scale1, y2 + pixmap2.getHeight() * scale2);

        // If there is no overlap, return false
        if (overlapXStart >= overlapXEnd || overlapYStart >= overlapYEnd) {
            return false;
        }

        // Convert overlap region to integer bounds for iteration
        int startX = (int) Math.floor(overlapXStart);
        int startY = (int) Math.floor(overlapYStart);
        int endX = (int) Math.ceil(overlapXEnd);
        int endY = (int) Math.ceil(overlapYEnd);

        // Check pixels in the overlapping region
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                // Convert global coordinates to local Pixmap coordinates, accounting for scaling
                int pixmap1X = (int) ((x - x1) / scale1);
                int pixmap1Y = pixmap1.getHeight() - 1 - (int) ((y - y1)/ scale1); // Flip Y for Pixmap 1


                int pixmap2X = (int) ((x - x2) / scale2);
                int pixmap2Y = pixmap2.getHeight() - 1 - (int) ((y - y2)/ scale2); // Flip Y for Pixmap 1


                // Ensure indices are within bounds for each Pixmap
                if (pixmap1X >= 0 && pixmap1X < pixmap1.getWidth() &&
                    pixmap1Y >= 0 && pixmap1Y < pixmap1.getHeight() &&
                    pixmap2X >= 0 && pixmap2X < pixmap2.getWidth() &&
                    pixmap2Y >= 0 && pixmap2Y < pixmap2.getHeight()) {

                    // Get pixel colors
                    int color1 = pixmap1.getPixel(pixmap1X, pixmap1Y);
                    int color2 = pixmap2.getPixel(pixmap2X, pixmap2Y);

                    // Check if both pixels are non-transparent
                    if ((color1 & 0x000000FF) > 0 && (color2 & 0x000000FF) > 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public void handleCollision(Entity secondEntity) {
        boolean isEntityAnimationPhase = entity.animationOfEntity.shouldDisplayAnimation == -1;

        boolean isSecondEntityAnimationPhase = secondEntity.animationOfEntity.shouldDisplayAnimation == -1;
        if(isEntityAnimationPhase &&
            isSecondEntityAnimationPhase) {
            if (overlapsSpriteHitbox(secondEntity)) {
                this.entity.gotHit();
                secondEntity.gotHit();

            }
        }
        else if(!isEntityAnimationPhase && isSecondEntityAnimationPhase){
            if(secondEntity instanceof BeamShooter || secondEntity instanceof UFO) {
                if (entity.hitboxOfEntity.animationOverlapsSpriteHitbox(secondEntity)) {
                    this.entity.gotHit();
                }
            }
        }
        else if(isEntityAnimationPhase && !isSecondEntityAnimationPhase){
            if(secondEntity instanceof BeamShooter || secondEntity instanceof UFO) {
                if (secondEntity.hitboxOfEntity.animationOverlapsSpriteHitbox(this.entity)) {
                    this.entity.gotHit();
                }
            }
        }

    }
}
