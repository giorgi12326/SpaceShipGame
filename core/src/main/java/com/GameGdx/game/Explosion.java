//package com.GameGdx.game;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//
//public class Explosion extends Bullet{
//    public static float spawnSpeed = 1f;
//    public static float timer = 0;
//    public boolean expired = false;
//
//    public Explosion(float x,float y){
//        spriteOfEntity.scale = 8f;
//
//        spriteOfEntity.texture = new Texture("explode.png");
//        spriteOfEntity.sprite = new Sprite(spriteOfEntity.texture);
//        spriteOfEntity.sprite.setScale(spriteOfEntity.scale);
//        spriteOfEntity.sprite.setX(x);
//        spriteOfEntity.sprite.setY(y);
//
//        spriteOfEntity.height = spriteOfEntity.sprite.getHeight();
//        spriteOfEntity.width = spriteOfEntity.sprite.getWidth();
//
//        hitboxOfEntity.hitboxWidth = spriteOfEntity.height;
//        hitboxOfEntity.hitboxHeight = spriteOfEntity.height;
//
//
//        Texture expImage = new Texture("explode.png");
//
//        spriteOfEntity.width = 150f;
//        spriteOfEntity.height = 60f;
//
//        TextureRegion[][] expRegion = TextureRegion.split(expImage,(int) spriteOfEntity.width,(int) spriteOfEntity.height);
//        animationOfEntity.animations.add(new Animation<>(0.1f, expRegion[0]));
//        animationOfEntity.shouldDisplayAnimation = 0;
//        animationOfEntity.framesOfAnimation = 5;
//
//    }
//
//    @Override
//    public void hitBoxDuringAnimation() {
//        hitboxOfEntity.setRectangle();
//        System.out.println(hitboxOfEntity.rectangle.getWidth() + " " + hitboxOfEntity.rectangle.getHeight());
//    }
////    public boolean pixelPerfectCollision(Pixmap pixmap1, int x1, int y1,
////                                     Pixmap pixmap2, int x2, int y2) {
////    for (int i = 0; i < pixmap1.getWidth(); i++) {
////        for (int j = 0; j < pixmap1.getHeight(); j++) {
////            int globalX = x1 + i;
////            int globalY = y1 + j;
////
////            int pixmap2X = globalX - x2;
////            int pixmap2Y = globalY - y2;
////
////            if (pixmap2X >= 0 && pixmap2Y >= 0 &&
////                pixmap2X < pixmap2.getWidth() &&
////                pixmap2Y < pixmap2.getHeight()) {
////
////                int pixel1 = pixmap1.getPixel(i, j);
////                int pixel2 = pixmap2.getPixel(pixmap2X, pixmap2Y);
////
////                if (((pixel1 & 0x000000FF) > 0) && // Alpha > 0
////                    ((pixel2 & 0x000000FF) > 0)) {
////                    return true;
////                }
////            }
////        }
////    }
////    return false;
////}
//}
