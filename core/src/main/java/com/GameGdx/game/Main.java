package com.GameGdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Color;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Texture laserImage;
    Sprite shipSprite;
    Texture lShip;
    Texture rShip;
    Texture nShip;
    List<Sprite> bulletList;
    float bulletTimer;
    Texture backgroundTexture;
    private Texture enemyTexture;
    Random random;
    float backgroundWidth;
    float backgroundHeight;
    List<Sprite> enemyList;
    float enemyTimer;
    List<Rectangle> enemyRectangleList;
    List<Rectangle> bulletRectangleList;
    ShapeRenderer shapeRenderer;
    Sprite backgroundSprite;
    boolean isTiltedLeft = false;
    boolean isTiltedRight = false;


    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        backgroundWidth = Gdx.graphics.getWidth();
        backgroundHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        lShip = new Texture("Playerplaneleft.png");
        rShip = new Texture("Playerplaneright.png");
        nShip = new Texture("plane.png");
        shipSprite = new Sprite(nShip);

        shipSprite.setScale(2.0f);
        bulletList = new ArrayList<>();
        laserImage = new Texture("laser.png");
        backgroundTexture = new Texture ("bg.png");
        enemyTexture = new Texture("enemy.png");
        random = new Random();
        enemyList = new ArrayList<>();
        enemyRectangleList = new ArrayList<>();
        bulletRectangleList = new ArrayList<>();

        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setScale(4);
        backgroundSprite.setX(backgroundWidth*3/8);
        backgroundSprite.setY(backgroundHeight*3/8);

    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }
    Rectangle r;

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();

        backgroundSprite.draw(batch);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            shipSprite.setTexture(lShip);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            shipSprite.setTexture(rShip);
        else
            shipSprite.setTexture(nShip);
        //TODO SHOOTING ANIMATION

        shipSprite.draw(batch);

        for(Sprite sprite : bulletList) {
            sprite.draw(batch);
        }
        for(Sprite sprite : enemyList) {
            sprite.draw(batch);

        }
        batch.end();
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);  // Use Filled for a solid rectangle or Line for an outline
//        shapeRenderer.setColor(Color.RED);
//        for(Rectangle bulletRectangle: bulletRectangleList)
//            shapeRenderer.rect(bulletRectangle.getX(), bulletRectangle.getY(), bulletRectangle.width, bulletRectangle.height);  // Position and dimensions of the rectangle
//        for(Rectangle enemyRectangle: enemyRectangleList)
//            shapeRenderer.rect(enemyRectangle.getX(), enemyRectangle.getY(), enemyRectangle.getWidth(), enemyRectangle.getHeight());  // Position and dimensions of the rectangle
//
//        shapeRenderer.end();
    }
    private void logic() {
        float delta = Gdx.graphics.getDeltaTime();
        for (int i = bulletList.size() - 1; i >= 0; i--) {
            Sprite bulletSprite = bulletList.get(i);
            Rectangle bulletRectangle = bulletRectangleList.get(i);
            bulletSprite.translateY(1000f * delta);

            bulletRectangle.set(bulletSprite.getX() + (bulletSprite.getWidth() - bulletSprite.getWidth() * bulletSprite.getScaleX())/2,
                bulletSprite.getY() + (bulletSprite.getHeight() -bulletSprite.getHeight()*bulletSprite.getScaleY())/2,bulletSprite.getWidth() * bulletSprite.getScaleX(),bulletSprite.getHeight()*bulletSprite.getScaleY());
            for (int j = enemyList.size() - 1; j >= 0; j--)
                if(enemyRectangleList.get(j).overlaps(bulletRectangle)){
                    enemyRectangleList.remove(j);
                    enemyList.remove(j);
                    bulletList.remove(i);
                }
            if(bulletSprite.getY() > Gdx.graphics.getHeight()) {
                bulletRectangleList.remove(i);
                bulletList.remove(i);

            }
        }
        for (int i = enemyList.size() - 1; i >= 0; i--) {
            Sprite enemySprite = enemyList.get(i);
            Rectangle enemyRectangle = enemyRectangleList.get(i);
            enemySprite.translateY(-200f * delta);
//            if(enemyRectangle.overlaps(bulletRectangle))
//                enemyList.remove(i);
            enemyRectangle.set(enemySprite.getX() + (enemySprite.getWidth() - enemySprite.getWidth() * enemySprite.getScaleX())/2,
                enemySprite.getY() + (enemySprite.getHeight() -enemySprite.getHeight()*enemySprite.getScaleY())/2,enemySprite.getWidth() * enemySprite.getScaleX(),enemySprite.getHeight()*enemySprite.getScaleY());

            if(enemySprite.getY() < -enemySprite.getHeight()) {
                enemyList.remove(i);
                enemyRectangleList.remove(i);
            }

        }

        enemyTimer+= delta;
        if(enemyTimer > 0.3f){
            enemyTimer = 0;
            createEnemy();
        }


    }

    private void input() {
        float speed = 300.0f;
        float delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            shipSprite.translateX(delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            shipSprite.translateX(-delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            shipSprite.translateY(delta * speed);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            shipSprite.translateY(-delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            if(bulletTimer > 0.2f){
                bulletTimer = 0;
                createBullet();
        }
        bulletTimer+= delta;
    }

    private void createBullet() {
        float distanceFromCenter = 25.0f;

        Sprite laserSprite1 = new Sprite(laserImage);
        laserSprite1.setX(shipSprite.getX() - shipSprite.getWidth()*0.5f );
        laserSprite1.setY(shipSprite.getY()-20);
        laserSprite1.setScale(0.5f);

        Sprite laserSprite2 = new Sprite(laserImage);
        laserSprite2.setX(shipSprite.getX() + shipSprite.getWidth()*1.5f- laserSprite1.getWidth());
        laserSprite2.setY(shipSprite.getY()-20);
        laserSprite2.setScale(0.5f);

        Rectangle bulletRectangle1 = new Rectangle();
        Rectangle bulletRectangle2 = new Rectangle();

        bulletRectangleList.add(bulletRectangle1);
        bulletRectangleList.add(bulletRectangle2);
        bulletList.add(laserSprite1);
        bulletList.add(laserSprite2);

    }
    private void createEnemy(){
        Sprite enemySprite = new Sprite(enemyTexture);
        enemySprite.setX(random.nextFloat(3f/8f*enemySprite.getWidth(),backgroundWidth-1.5f*enemySprite.getWidth()));
        enemySprite.setY(backgroundHeight);
        enemySprite.setScale(4f);

        Rectangle enemyRectangle = new Rectangle();
        enemyRectangleList.add(enemyRectangle);
        enemyList.add(enemySprite);

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }


}
