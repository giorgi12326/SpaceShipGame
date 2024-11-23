package com.GameGdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    Sprite shipSprite;
    Texture lShip;
    Texture rShip;
    Texture nShip;
    List<Bullet> bullets;
    float backgroundWidth;
    float backgroundHeight;
    Sound shoot;
    Sound shootSound;
    Sound explosionSound;
    Music gameMusic;
    float enemyTimer;
    Texture backgroundTexture;
    Sprite backgroundSprite;
    ShapeRenderer shapeRenderer;
    boolean isTiltedLeft = false;
    boolean isTiltedRight = false;
    List<Enemy> enemies;
    List<Entity> pendingAnimations;
    List<Entity> garbageCollector;
//    List<Explosion> explosionList;
    Music pop ;
    Circle rectangle;
    float shipTimer = 0f;
    float dashTimer = 1f;
    Ship ship;


    @Override
    public void create() {
        rectangle = new Circle();
        shapeRenderer = new ShapeRenderer();
        backgroundWidth = Gdx.graphics.getWidth();
        backgroundHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        lShip = new Texture("Playerplaneleft.png");
        rShip = new Texture("Playerplaneright.png");
        nShip = new Texture("plane.png");
        shipSprite = new Sprite(nShip);
        shipSprite.setScale(2.0f);
        bullets = new ArrayList<>();
        backgroundTexture = new Texture ("bg.png");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setScale(4);
        backgroundSprite.setX(backgroundWidth*3/8);
        backgroundSprite.setY(backgroundHeight*3/8);
        enemies = new ArrayList<>();
        pendingAnimations = new ArrayList<>();
        shootSound = Gdx.audio.newSound(Gdx.files.internal("shootSound.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("gameMusic.mp3"));
        pop = Gdx.audio.newMusic(Gdx.files.internal("pop.mp3"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(1f);
        gameMusic.play();
        garbageCollector = new ArrayList<>();
//        explosionList = new ArrayList<>();
        ship = new Ship();

    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    //sprite getWidth and getX return original values
    //rectangle starts and scales and sprites original position
    //but circle scales from its center which is its original coordinates


    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        batch.begin();

        backgroundSprite.draw(batch);
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
//            ship.sprite.setTexture(lShip);
//        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
//            ship.sprite.setTexture(rShip);
//        else
//            ship.sprite.setTexture(nShip);
        //TODO SHOOTING ANIMATION

        ship.update(batch);

        for(Bullet bullet : bullets) {
            bullet.update(batch);
        }
        for(Enemy enemy : enemies) {
            enemy.update(batch);
        }
        batch.end();
        for(Enemy enemy : enemies) {

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Use ShapeType.Line for outline rectangles

            // Draw a rectangle (x, y, width, height)
            shapeRenderer.setColor(Color.RED); // Set color to red
            shapeRenderer.rect(enemy.spriteOfEntity.sprite.getX(), enemy.spriteOfEntity.sprite.getY(), enemy.hitboxOfEntity.hitboxWidth* enemy.spriteOfEntity.scale, enemy.hitboxOfEntity.hitboxHeight*enemy.spriteOfEntity.scale); // Rectangle at (50,50) with width 100 and height 200


            // End rendering shapes
            shapeRenderer.end();
        }

    }
    private void logic() {
        float delta = Gdx.graphics.getDeltaTime();

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Entity enemy = enemies.get(i);
            if(enemy.hitboxOfEntity.rectangle.overlaps(ship.hitboxOfEntity.rectangle))
                System.exit(1);
        }


        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
//            bullet.sprite.translateY(1000f * delta);

//            bullet.rectangle.set(bullet.sprite.getX() + (bullet.sprite.getWidth() - bullet.sprite.getWidth() * bullet.sprite.getScaleX())/2,
//                bullet.sprite.getY() + (bullet.sprite.getHeight() -bullet.sprite.getHeight()*bullet.sprite.getScaleY())/2,bullet.sprite.getWidth() * bullet.sprite.getScaleX(),bullet.sprite.getHeight()*bullet.sprite.getScaleY());

            for (int j = enemies.size() - 1; j >= 0; j--) {
                Enemy enemy = enemies.get(j);
                if (enemy.hitboxOfEntity.rectangle.overlaps(bullet.hitboxOfEntity.rectangle)) {

                    if(bullet instanceof Rocket) {
                        bullet.animationOfEntity.triggerAnimation();
//                        Explosion explosion = new Explosion(bullet.spriteOfEntity.sprite.getX() + bullet.spriteOfEntity.width /2, bullet.spriteOfEntity.sprite.getY()+ bullet.spriteOfEntity.height /2);
//                        explosionSound.play();
//                        bullets.add(explosion);
//                        garbageCollector.add(explosion);
                    }

                    enemy.animationOfEntity.shouldDisplayAnimation = 0;
                    garbageCollector.add(enemies.get(j));
                    garbageCollector.add(bullets.get(i));
                    pop.play();
                }
            }
            if(bullet.spriteOfEntity.sprite.getY() > Gdx.graphics.getHeight()) {
                garbageCollector.add(bullets.get(i));
            }
        }
//        for (Explosion explosion : explosionList) {
//            for (int i = enemies.size() - 1; i >= 0; i--) {
//                Enemy enemy =enemies.get(i);
//                if (enemy.rectangle.overlaps(explosion.rectangle)) {
//
//                    pendingAnimations.add(enemy);
//                    pendingAnimations.add(explosion);
//                    System.out.println("here");
//dw
//                    garbageCollector.add(enemies.get(i));
//                }
//            }
//        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            if(enemy.spriteOfEntity.sprite.getY() < 0){
                garbageCollector.add(enemies.get(i));
//                System.exit(1);//dedda
            }
        }

        for (int i = garbageCollector.size() - 1; i >= 0; i--) {
            Entity entity = garbageCollector.get( i);

            if(entity instanceof Bullet){
                if (entity.animationOfEntity.shouldDisplayAnimation == -1) {
                    bullets.remove(entity);
                    garbageCollector.remove(entity);
                }
            }
            else if(entity instanceof Enemy) {
                if (entity.animationOfEntity.shouldDisplayAnimation == -1) {
                    enemies.remove(entity);
                    garbageCollector.remove(entity);
                }
            }

        }
        Rock.timer += delta;
        if(Rock.timer > Rock.spawnSpeed){
            Rock.timer = 0;
            createEnemy();
        }
        UFO.timer += delta;
        if(UFO.timer > UFO.spawnSpeed){
            UFO.timer = 0;
            createUFO();
        }
    }

    private void input() {
        float speed = 300.0f;
        float delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(ship.spriteOfEntity.sprite.getX() < Gdx.graphics.getWidth() + ship.spriteOfEntity.width /2*(-1f- ship.spriteOfEntity.scale))
                ship.spriteOfEntity.sprite.translateX(delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(ship.spriteOfEntity.sprite.getX() > -ship.spriteOfEntity.width /2*(1f/ ship.spriteOfEntity.scale - 1f))//
                ship.spriteOfEntity.sprite.translateX(-delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            ship.spriteOfEntity.sprite.translateY(delta * speed);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            ship.spriteOfEntity.sprite.translateY(-delta * speed);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            System.out.println(shipTimer + " " + dashTimer);

            if(shipTimer > dashTimer) {
                if(ship.spriteOfEntity.sprite.getX() > -ship.spriteOfEntity.width /2*(1f/ship.spriteOfEntity.scale - 1))//
                    ship.spriteOfEntity.sprite.translateX(-delta * speed * 40);
                shipTimer = 0f;
                ship.animationOfEntity.triggerAnimation();
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            if(shipTimer > dashTimer) {
                if(ship.spriteOfEntity.sprite.getX() < Gdx.graphics.getWidth() + ship.spriteOfEntity.width /2*(1f/ ship.spriteOfEntity.scale - 1f))
                    ship.spriteOfEntity.sprite.translateX(+delta * speed * 40);
                shipTimer = 0f;
                ship.animationOfEntity.triggerAnimation();
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (Lasers.timer > Lasers.spawnSpeed) {
                shootSound.play();
                createLasers();
                Lasers.timer = 0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            if(Rocket.timer > Rocket.spawnSpeed){
                createRocket();
                Rocket.timer = 0;
            }

        }
        Rocket.timer+= delta;
        Lasers.timer+= delta;
        shipTimer+=delta;
    }

    private void createLasers() {
        float distanceFromCenter = 25.0f;
        Bullet bullet1 = new Lasers(ship.spriteOfEntity.sprite.getX() - ship.spriteOfEntity.sprite.getWidth()*0.5f, ship.spriteOfEntity.sprite.getY()-20);
        Bullet bullet2 = new Lasers(ship.spriteOfEntity.sprite.getX() + ship.spriteOfEntity.sprite.getWidth()*1.5f- bullet1.spriteOfEntity.width/bullet1.spriteOfEntity.scale, ship.spriteOfEntity.sprite.getY()-20);
        bullets.add(bullet1);
        bullets.add(bullet2);

    }
    private void createRocket() {
        Bullet rocket = new Rocket(ship.spriteOfEntity.sprite.getX() + ship.spriteOfEntity.sprite.getWidth()*1.5f- 15, ship.spriteOfEntity.sprite.getY()-20);
        bullets.add(rocket);

    }
    private void createUFO(){
        Enemy ufo = new UFO(ship);
        enemies.add(ufo);
    }
    private void createEnemy(){
        Enemy enemy = new Rock();
        enemies.add(enemy);
    }
//    private float[] scaledEntityParameters(Entity entity){
//        float[] arr = new float[4];
//        arr[0] = entity.sprite.getX() + entity.sprite.getWidth()*Math.abs((1f-entity.scale))/2;
//        arr[1] = entity.sprite.getY() + entity.sprite.getHeight()*Math.abs((1f-entity.scale))/2;
//        arr[2] = entity.width;
//        arr[3] = entity.height;
//        return arr;
//    }


    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();

    }



}
