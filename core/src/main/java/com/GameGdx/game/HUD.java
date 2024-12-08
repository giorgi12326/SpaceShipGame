package com.GameGdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
    Ship ship;
    public HUD(Ship ship){
        this.ship = ship;
        for (int i = 0; i < hearts.length; i++) {
            hearts[i] = new Sprite(new Texture("heart.png"));
        }
    }
    Sprite[] hearts = new Sprite[3];
    public void update(SpriteBatch batch){
        drawHeart(batch);
    }

    private void drawHeart(SpriteBatch batch) {
        for (int i = 0; i < ship.hearts; i++) {
            Sprite heart = hearts[i];
            heart.setScale(3);
            heart.setX((heart.getWidth()*heart.getScaleX()/2f - heart.getWidth()/2f) + (heart.getWidth()*heart.getScaleX())*(i));
            heart.setY(Gdx.graphics.getHeight()-heart.getHeight()/2f - heart.getHeight()*heart.getScaleY()/2f);
            hearts[i].draw(batch);
        }
    }
}
