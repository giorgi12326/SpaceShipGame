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
//        createEnemy();


    }

    @Override
    public void render() {
        input();
        logic();
        draw();
        lines();//has to be blow DRAW !

//        test();
    }

    private void lines() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(Enemy enemy : enemies) {
            if(enemy instanceof UFO&& enemy.animationOfEntity.shouldDisplayAnimation != -1){
                shapeRenderer.setColor(Color.BLUE);
                Pair getHitbox = enemy.animationOfEntity.hitbox.getFirst();
                Pair getOffset = enemy.animationOfEntity.offset.getFirst();
                shapeRenderer.rect(enemy.spriteOfEntity.sprite.getX() + enemy.spriteOfEntity.sprite.getWidth()/2f - getHitbox.x()/2f + getOffset.x(),
                    enemy.spriteOfEntity.sprite.getY() + enemy.spriteOfEntity.sprite.getHeight()/2f - getHitbox.y()/2f + getOffset.y(),
                    getHitbox.x(),getHitbox.y());
            }
            else {
                shapeRenderer.setColor(Color.RED);
                shapeRenderer.rect(
                    enemy.spriteOfEntity.sprite.getX() - enemy.hitboxOfEntity.hitboxWidth / 2f + enemy.spriteOfEntity.sprite.getWidth() / 2f,
                    enemy.spriteOfEntity.sprite.getY() - enemy.hitboxOfEntity.hitboxHeight / 2f + enemy.spriteOfEntity.sprite.getHeight() / 2f,
                    enemy.hitboxOfEntity.hitboxWidth,
                    enemy.hitboxOfEntity.hitboxHeight
                );
                shapeRenderer.setColor(Color.YELLOW);


            }

        }
        shapeRenderer.rect(ship.spriteOfEntity.sprite.getX() - ship.spriteOfEntity.width/2f + ship.spriteOfEntity.sprite.getWidth()/2f,
            ship.spriteOfEntity.sprite.getY() - ship.spriteOfEntity.height/2f  + ship.spriteOfEntity.sprite.getHeight()/2f,
            ship.spriteOfEntity.width,ship.spriteOfEntity.height
        );
        for(Bullet enemy : bullets) {
            shapeRenderer.setColor(Color.RED);
            if(enemy.animationOfEntity.shouldDisplayAnimation == -1) {

                shapeRenderer.rect(
                    enemy.spriteOfEntity.sprite.getX() - enemy.hitboxOfEntity.hitboxWidth / 2f + enemy.spriteOfEntity.sprite.getWidth() / 2f,
                    enemy.spriteOfEntity.sprite.getY() - enemy.hitboxOfEntity.hitboxHeight / 2f + enemy.spriteOfEntity.sprite.getHeight() / 2f,
                    enemy.hitboxOfEntity.hitboxWidth,
                    enemy.hitboxOfEntity.hitboxHeight
                );
            }
            else {
                Pair getHitbox = enemy.animationOfEntity.hitbox.getFirst();
                Pair getOffset = enemy.animationOfEntity.offset.getFirst();
                shapeRenderer.rect( enemy.spriteOfEntity.sprite.getX() - enemy.animationOfEntity.sizeFull.get(enemy.animationOfEntity.shouldDisplayAnimation).x()/2f
                        + enemy.spriteOfEntity.sprite.getWidth()/2f
                        + enemy.animationOfEntity.offset.get(enemy.animationOfEntity.shouldDisplayAnimation).x(),
                    enemy.spriteOfEntity.sprite.getY() - enemy.animationOfEntity.sizeFull.get(enemy.animationOfEntity.shouldDisplayAnimation).y()/2f
                        + enemy.spriteOfEntity.sprite.getHeight()/2f
                        + enemy.animationOfEntity.offset.get(enemy.animationOfEntity.shouldDisplayAnimation).y(),
                    enemy.animationOfEntity.sizeFull.get(enemy.animationOfEntity.shouldDisplayAnimation).x(),
                    enemy.animationOfEntity.sizeFull.get(enemy.animationOfEntity.shouldDisplayAnimation).y()

                );

            }

        }
        shapeRenderer.end();
    }
//
//    private void test() {
//
//        Entity enemy = enemies.get(0);
//        enemy.spriteOfEntity.sprite.setY(0);
//        enemy.spriteOfEntity.sprite.setX(0);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//
//            shapeRenderer.setColor(Color.BLUE);
//            shapeRenderer.rect(
//                enemy.spriteOfEntity.sprite.getX() - enemy.spriteOfEntity.width/2 + enemy.spriteOfEntity.sprite.getWidth()/2,
//                enemy.spriteOfEntity.sprite.getY(),
//                enemy.hitboxOfEntity.hitboxWidth,
//                enemy.hitboxOfEntity.hitboxHeight
//            );
//
//
//        shapeRenderer.end();
//    }

    //sprite getWidth and getX return original values(--WITHOUT-- REMOVAL OF TRANSPARENCY) and scales without its coordinates changing**\
        //however it scales from the center and x and y no longer represent starting point of sprite
        //my width is now scaled width (with transparency),
        // if you want without one, you look for hit-boxWidth
        //getY and getY doesnt change after scaling
    //rectangle starts and scales and sprites original position
    //but circle scales from its center which is its original coordinates


    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        batch.begin();

        backgroundSprite.draw(batch);
        //TODO SHOOTING ANIMATION

        ship.update(batch);

        for(Bullet bullet : bullets) {
            bullet.update(batch);
        }
        for(Enemy enemy : enemies) {
            enemy.update(batch);
        }

        batch.end();
    }
    private void logic() {
        float delta = Gdx.graphics.getDeltaTime();

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

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Entity enemy = enemies.get(i);
            if(enemy.hitboxOfEntity.rectangle.overlaps(ship.hitboxOfEntity.rectangle)) {
//                if (ship.hitboxOfEntity.overlapsSpriteHitbox(enemy))//
//                    System.out.println("yes");
//                System.out.println(ship.hitboxOfEntity.overlapsHitbox(enemy));
//                System.exit(1);
            }
        }

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            for (int j = enemies.size() - 1; j >= 0; j--) {
                Enemy enemy = enemies.get(j);
                if (enemy.hitboxOfEntity.rectangle.overlaps(bullet.hitboxOfEntity.rectangle)) {
                    if(bullet.animationOfEntity.shouldDisplayAnimation == -1) {
                        if (enemy.hitboxOfEntity.overlapsSpriteHitbox(bullet)) {
                            if (bullet instanceof Rocket) {
                                bullet.animationOfEntity.triggerAnimation();
                                explosionSound.play();
                            }
                            enemy.animationOfEntity.triggerAnimation();
                            garbageCollector.add(enemy);
                            garbageCollector.add(bullet);
                            pop.play();

                        }
                    }
                    else{
                        if(bullet.hitboxOfEntity.animationOverlapsSpriteHitbox(enemy)){
                            enemy.animationOfEntity.triggerAnimation();
                            garbageCollector.add(enemy);
                            garbageCollector.add(bullet);
                            pop.play();
                        }
                    }
                }


                if (bullet.spriteOfEntity.sprite.getY() > Gdx.graphics.getHeight()) {
                    garbageCollector.add(bullets.get(i));
                }
            }
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            if(enemy.spriteOfEntity.sprite.getY() < 0){
                garbageCollector.add(enemies.get(i));
//                System.exit(1);//dedda
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
        if(Gdx.input.isKeyPressed(Input.Keys.P)) {
            createBeamLaser();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(ship.spriteOfEntity.sprite.getX() + ship.spriteOfEntity.sprite.getWidth()/2f +ship.spriteOfEntity.width/2f  < Gdx.graphics.getWidth())
                ship.spriteOfEntity.sprite.translateX(delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(ship.spriteOfEntity.sprite.getX() + ship.spriteOfEntity.sprite.getWidth()/2f -ship.spriteOfEntity.width/2f > 0)//
                ship.spriteOfEntity.sprite.translateX(-delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            if(ship.spriteOfEntity.sprite.getY() + ship.spriteOfEntity.sprite.getHeight()/2f +ship.spriteOfEntity.height/2f  < Gdx.graphics.getHeight())
                ship.spriteOfEntity.sprite.translateY(delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(ship.spriteOfEntity.sprite.getY() + ship.spriteOfEntity.sprite.getHeight()/2f -ship.spriteOfEntity.height/2f > 0)//
                ship.spriteOfEntity.sprite.translateY(-delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            if(shipTimer > dashTimer) {
                if(ship.spriteOfEntity.sprite.getX() + ship.spriteOfEntity.sprite.getWidth()/2f - ship.spriteOfEntity.width/2f - delta * speed * 30 >= 0)
                    ship.spriteOfEntity.sprite.translateX(-delta * speed * 30);
                else
                    ship.spriteOfEntity.sprite.setX(ship.spriteOfEntity.width / 2f - ship.spriteOfEntity.sprite.getWidth()/ 2f);

                shipTimer = 0f;
                ship.animationOfEntity.triggerAnimation();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            if(shipTimer > dashTimer) {
                if (ship.spriteOfEntity.sprite.getX() + ship.spriteOfEntity.sprite.getWidth() / 2f + ship.spriteOfEntity.width / 2f + delta * speed * 30 < Gdx.graphics.getWidth())
                    ship.spriteOfEntity.sprite.translateX(delta * speed * 30);
                else
                    ship.spriteOfEntity.sprite.setX(Gdx.graphics.getWidth() - ship.spriteOfEntity.sprite.getWidth() / 2f - ship.spriteOfEntity.width / 2f);
                shipTimer = 0f;
                ship.animationOfEntity.triggerAnimation(1);
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
        Bullet bullet2 = new Lasers(ship.spriteOfEntity.sprite.getX() + ship.spriteOfEntity.sprite.getWidth()*1.5f- bullet1.spriteOfEntity.width, ship.spriteOfEntity.sprite.getY()-20);
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
    private void createBeamShooter(){
        Enemy beamShooter = new BeamShooter(ship);
        enemies.add(beamShooter);
    }
    private void createBeamLaser(){
        Enemy beamLaser = new BeamShooter(ship);
        enemies.add(beamLaser);

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
    }



}
