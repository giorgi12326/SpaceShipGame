package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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

    public void setSpriteRectangle() {
        rectangle.set(entity.spriteOfEntity.sprite.getX() - entity.hitboxOfEntity.hitboxWidth/2f + entity.spriteOfEntity.sprite.getWidth()/2f,
            entity.spriteOfEntity.sprite.getY() - entity.hitboxOfEntity.hitboxHeight/2f  + entity.spriteOfEntity.sprite.getHeight()/2f ,
                hitboxWidth, hitboxHeight);
    }
//    public void setRectangle(float x,float y,float width,float height,float scale) {
//        rectangle.set(entity.spriteOfEntity.sprite.getX()  - height * scale / 2, y - width * scale / 2,
//                width * scale, height * scale);
//    }
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
    public boolean overlapsHitbox(Entity secondEntity){
//        System.out.println("asdasdasd " +             secondEntity.spriteOfEntity.sprite.getY() + " " +  secondEntity.spriteOfEntity.height/2f+ " "  + secondEntity.spriteOfEntity.sprite.getHeight()/2f);

        return doPixmapsOverlap(pixmap,
            entity.spriteOfEntity.sprite.getX() - entity.spriteOfEntity.width/2f + entity.spriteOfEntity.sprite.getWidth()/2f,
            entity.spriteOfEntity.sprite.getY() - entity.spriteOfEntity.height/2f  + entity.spriteOfEntity.sprite.getHeight()/2f,
            secondEntity.hitboxOfEntity.pixmap,
            secondEntity.spriteOfEntity.sprite.getX() - secondEntity.spriteOfEntity.width/2f + secondEntity.spriteOfEntity.sprite.getWidth()/2f,
            secondEntity.spriteOfEntity.sprite.getY() - secondEntity.spriteOfEntity.height/2f  + secondEntity.spriteOfEntity.sprite.getHeight()/2f);
    }


    public boolean doPixmapsOverlap(Pixmap pixmap1, float x1, float y1,
                                    Pixmap pixmap2, float x2, float y2) {
        // Determine the overlap region
        float overlapXStart = Math.max(x1, x2);
        float overlapYStart = Math.max(y1, y2);
        float overlapXEnd = Math.min(x1 + pixmap1.getWidth(), x2 + pixmap2.getWidth());
        float overlapYEnd = Math.min(y1 + pixmap1.getHeight(), y2 + pixmap2.getHeight());

        // If there is no overlap, return false
        if (overlapXStart >= overlapXEnd || overlapYStart >= overlapYEnd) {
//            System.out.println("No overlap region");
            return false;
        }

        // Convert overlap region to integer bounds for iteration
        int startX = (int) Math.floor(overlapXStart);
        int startY = (int) Math.floor(overlapYStart);
        int endX = (int) Math.ceil(overlapXEnd);
        int endY = (int) Math.ceil(overlapYEnd);

        // Debugging: Print overlap region
//        System.out.printf("Overlap Region: (%d, %d) to (%d, %d)%n", startX, startY, endX, endY);

        // Check pixels in the overlapping region
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                // Convert global coordinates to local Pixmap coordinates
                // Convert global coordinates to local Pixmap coordinates
                int pixmap1X = (int) (x - x1);
                int pixmap1Y = pixmap1.getHeight() - 1 - (int) (y - y1); // Flip Y for Pixmap 1

                int pixmap2X = (int) (x - x2);
                int pixmap2Y = pixmap2.getHeight() - 1 - (int) (y - y2); // Flip Y for Pixmap 2


                // Ensure indices are within bounds for each Pixmap
                if (pixmap1X >= 0 && pixmap1X < pixmap1.getWidth() &&
                    pixmap1Y >= 0 && pixmap1Y < pixmap1.getHeight() &&
                    pixmap2X >= 0 && pixmap2X < pixmap2.getWidth() &&
                    pixmap2Y >= 0 && pixmap2Y < pixmap2.getHeight()) {

                    // Get pixel colors
                    int color1 = pixmap1.getPixel(pixmap1X, pixmap1Y);
                    int color2 = pixmap2.getPixel(pixmap2X, pixmap2Y);

                    // Debugging: Print color values
//                    System.out.printf("Checking pixels: pixmap1(%d, %d) = %x, pixmap2(%d, %d) = %x%n",
//                        pixmap1X, pixmap1Y, color1, pixmap2X, pixmap2Y, color2);

                    // Check if both pixels are non-transparent

//                    System.out.printf("Alpha1 = %d, Alpha2 = %d%n", alpha1, alpha2);

                    if (color1 != 0 && color2 != 0) { // Check if both pixels are non-transparent
                        System.out.println("Overlap detected at ship" + (pixmap1X) + ", " + (pixmap1Y) +" " + pixmap1.getPixel(pixmap1X,pixmap1Y));
                        System.out.println("Overlap detected at Rock" + (pixmap2X) + ", " + (pixmap2Y));
                        return true;
                    }

                }
            }
        }

//        System.out.println("No overlapping pixels found");
        return false; // No overlap found
    }

    public Pixmap scalePixmap(Pixmap original, float scaleX, float scaleY) {
        // Calculate new dimensions based on scale factors
        int newWidth = Math.round(original.getWidth() * scaleX);
        int newHeight = Math.round(original.getHeight() * scaleY);

        // Create a blank Pixmap with the new dimensions
        Pixmap scaled = new Pixmap(newWidth, newHeight, original.getFormat());

        // Draw the original Pixmap onto the scaled Pixmap, scaling it in the process
        scaled.drawPixmap(original,
            0, 0, original.getWidth(), original.getHeight(), // Source dimensions
            0, 0, newWidth, newHeight                       // Destination dimensions
        );

        return scaled; // Return the scaled Pixmap
    }









}
