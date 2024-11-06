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


    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        backgroundWidth = Gdx.graphics.getWidth();
        backgroundHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        Texture shipImage = new Texture("ship.png");
        TextureRegion[][] regions = TextureRegion.split(shipImage,17,32);
        shipSprite = new Sprite(regions[0][0]);
        bulletList = new ArrayList<>();
        laserImage = new Texture("laser.png");
        backgroundTexture = new Texture ("bg.png");
        enemyTexture = new Texture("enemy.png");
        random = new Random();
        enemyList = new ArrayList<>();
        enemyRectangleList = new ArrayList<>();
        bulletRectangleList = new ArrayList<>();

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

        Sprite backgroundSprite = new Sprite(backgroundTexture);
        float k = Math.min(backgroundTexture.getWidth()/backgroundWidth,
            backgroundTexture.getHeight()/backgroundHeight);

        backgroundSprite.setSize(backgroundTexture.getWidth()/k, backgroundTexture.getHeight()/k);
        batch.begin();

        backgroundSprite.draw(batch);
        shipSprite.draw(batch);
        for(Sprite sprite : bulletList) {
            sprite.draw(batch);
        }
        for(Sprite sprite : enemyList) {
            sprite.draw(batch);

        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);  // Use Filled for a solid rectangle or Line for an outline
        shapeRenderer.setColor(Color.BLUE);
        for (Sprite enemy: enemyList)
            shapeRenderer.rect(enemy.getX(),enemy.getY(),enemy.getWidth() * enemy.getScaleX(),enemy.getHeight()* enemy.getScaleY());
        shapeRenderer.setColor(Color.RED);
        for(Rectangle bulletRectangle: bulletRectangleList)
            shapeRenderer.rect(bulletRectangle.getX(), bulletRectangle.getY(), bulletRectangle.width, bulletRectangle.height);  // Position and dimensions of the rectangle
        for(Rectangle enemyRectangle: enemyRectangleList)
            shapeRenderer.rect(enemyRectangle.getX(), enemyRectangle.getY(), enemyRectangle.getWidth(), enemyRectangle.getHeight());  // Position and dimensions of the rectangle

        shapeRenderer.end();
    }
    private void logic() {
        Rectangle r = new Rectangle();
        r.set(-20,-20,100,100);

        float delta = Gdx.graphics.getDeltaTime();
        for (int i = bulletList.size() - 1; i >= 0; i--) {
            Sprite bulletSprite = bulletList.get(i);
            bulletSprite.translateY(1000f * delta);


            if(bulletSprite.getY() > Gdx.graphics.getHeight()) {
                bulletList.remove(i);
            }
        }

        for (int i = enemyList.size() - 1; i >= 0; i--) {
            Sprite enemySprite = enemyList.get(i);
            Rectangle enemyRectangle = enemyRectangleList.get(i);
            enemySprite.translateY(-200f * delta);
//            if(enemyRectangle.overlaps(bulletRectangle))
//                enemyList.remove(i);
            enemyRectangle.set(enemySprite.getX() + enemySprite.getWidth() - enemySprite.getWidth() * enemySprite.getScaleX(),
                enemySprite.getY() + enemySprite.getHeight() -enemySprite.getHeight()*enemySprite.getScaleY(),enemySprite.getWidth() * enemySprite.getScaleX(),enemySprite.getHeight()*enemySprite.getScaleY());

            if(enemySprite.getY() < -enemySprite.getHeight()) {
                enemyList.remove(i);
                enemyRectangleList.remove(i);
            }

        }

        enemyTimer+= delta;
        if(enemyTimer > 1.0f){
            enemyTimer = 0;
            createEnemy();
        }


    }

    private void input() {
        float speed = 300.0f;
        float delta = Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            shipSprite.translateY(delta * speed);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            shipSprite.translateX(delta * speed);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            shipSprite.translateX(-delta*speed);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            shipSprite.translateY(-delta*speed);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            if(bulletTimer > 0.2f){
                bulletTimer = 0;
                createBullet();

        }
        bulletTimer+= delta;
    }

    private void createBullet() {
        Sprite laserSprite = new Sprite(laserImage);
        laserSprite.rotate(90);
        laserSprite.setX(shipSprite.getX() - shipSprite.getWidth()/2 - laserSprite.getHeight());
        laserSprite.setY(shipSprite.getY());
        laserSprite.setScale(0.5f);
        bulletList.add(laserSprite);
    }
    private void createEnemy(){
        Sprite enemySprite = new Sprite(enemyTexture);
        enemySprite.setX(random.nextFloat(backgroundWidth-enemySprite.getWidth()));
        enemySprite.setY(backgroundHeight);
        System.out.println(enemySprite.getX());
        enemySprite.setScale(0.3f);
        System.out.println(enemySprite.getX());



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
