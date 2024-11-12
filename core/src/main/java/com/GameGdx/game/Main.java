package com.GameGdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
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
    List<Explosion> explosionList;
    Music pop ;
    Circle rectangle;
    float shipTimer = 0f;
    float dashTimer = 2f;


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
        explosionList = new ArrayList<>();

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
//    private void test() {
//        batch.begin();
//        shipSprite.setScale(3f);
//        shipSprite.setX(100);
//        shipSprite.setY(100);
//        System.out.println(shipSprite.getX() + " " + shipSprite.getWidth() + "asd");
//
//        rectangle.set(shipSprite.getX(),shipSprite.getY(),shipSprite.getWidth());
//
//        shipSprite.draw(batch);
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shipSprite.setColor(Color.RED);
//
//        shapeRenderer.circle(shipSprite.getX() - shipSprite.getWidth()*shipSprite.getScaleX()/2 + shipSprite.getWidth()/2 ,
//            shipSprite.getY() - shipSprite.getWidth()*(shipSprite.getScaleY()-1)/2 ,
//            shipSprite.getWidth()*shipSprite.getScaleX());
//
//        shapeRenderer.end();
//
//        batch.end();
//    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
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

        for(Bullet bullet : bullets) {
            bullet.update(batch);
        }
        for(Enemy enemy : enemies) {
            enemy.update(batch);

        }
        for(int i = pendingAnimations.size()-1; i >=0 ;i--){

            Entity entity = pendingAnimations.get(i);
            if(entity instanceof Enemy) {
//               entity.update(batch);
            }
            else if(entity instanceof Explosion){
                entity.animationTimer += Gdx.graphics.getDeltaTime();
                Animation<TextureRegion> current = entity.animation;
                batch.draw(current.getKeyFrame(entity.animationTimer),entity.sprite.getX() - Explosion.widthOfRegion*entity.sprite.getScaleX()/2f ,
                    entity.sprite.getY() - Explosion.heightOfRegion*entity.sprite.getScaleY()/2f, Explosion.widthOfRegion*entity.sprite.getScaleX(), Explosion.heightOfRegion*entity.sprite.getScaleY());

                entity.rectangle.set(entity.sprite.getX() - Explosion.heightOfRegion*entity.sprite.getScaleX()/2 ,
                    entity.sprite.getY() - Explosion.heightOfRegion*entity.sprite.getScaleY()/2 +20,entity.sprite.getHeight()*entity.sprite.getScaleX(),entity.sprite.getHeight()*entity.sprite.getScaleY());
                if (current.getKeyFrameIndex(entity.animationTimer) == 2) {
                    explosionList.remove(entity);
                    pendingAnimations.remove(i);
                }
            }
        }
        batch.end();

    }
    private void logic() {
        float delta = Gdx.graphics.getDeltaTime();

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.sprite.translateY(1000f * delta);

//            bullet.rectangle.set(bullet.sprite.getX() + (bullet.sprite.getWidth() - bullet.sprite.getWidth() * bullet.sprite.getScaleX())/2,
//                bullet.sprite.getY() + (bullet.sprite.getHeight() -bullet.sprite.getHeight()*bullet.sprite.getScaleY())/2,bullet.sprite.getWidth() * bullet.sprite.getScaleX(),bullet.sprite.getHeight()*bullet.sprite.getScaleY());

            for (int j = enemies.size() - 1; j >= 0; j--) {
                Enemy enemy = enemies.get(j);
                if (enemy.rectangle.overlaps(bullet.rectangle)) {
                    if(bullet instanceof Rocket) {
                        Explosion explosion = new Explosion(bullet.sprite.getX(), bullet.sprite.getY());
                        explosionSound.play();

                        explosionList.add(explosion);
                    }
                    enemy.shouldDisplayAnimation = true;

                    pendingAnimations.add(enemy);
                    garbageCollector.add(enemies.get(j));
                    garbageCollector.add(bullets.get(i));
                    pop.play();
                }
            }
            if(bullet.sprite.getY() > Gdx.graphics.getHeight()) {
                garbageCollector.add(bullets.get(i));
            }
        }
        for (Explosion explosion : explosionList) {
            for (int i = enemies.size() - 1; i >= 0; i--) {
                Enemy enemy =enemies.get(i);
                if (enemy.rectangle.overlaps(explosion.rectangle)) {
                    pendingAnimations.add(enemy);
                    System.out.println("here");

                    garbageCollector.add(enemies.get(i));
                }
            }
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            if(enemy instanceof Rock)
             enemy.sprite.translateY(-Rock.moveSpeed* delta);

            if(enemy.sprite.getY() < 0)
                garbageCollector.add(enemies.get(i));
        }

        for (int i = garbageCollector.size() - 1; i >= 0; i--) {
            Entity entity = garbageCollector.get( i);
            if(entity instanceof Bullet)
                bullets.remove(entity);
            else if(entity instanceof Enemy) {
                if (entity.shouldDisplayAnimation == false) {
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
    }

    private void input() {
        float speed = 300.0f;
        float delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            shipSprite.translateX(delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            shipSprite.translateX(-delta * speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            shipSprite.translateY(delta * speed);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            shipSprite.translateY(-delta * speed);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            if(shipTimer > dashTimer) {
                shipSprite.translateX(-delta * speed * 30);
                shipTimer = 0f;
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
        Bullet bullet1 = new Lasers(shipSprite.getX() - shipSprite.getWidth()*0.5f,shipSprite.getY()-20);
        Bullet bullet2 = new Lasers(shipSprite.getX() + shipSprite.getWidth()*1.5f- bullet1.width,shipSprite.getY()-20);
        bullets.add(bullet1);
        bullets.add(bullet2);

    }
    private void createRocket() {
        Bullet rocket = new Rocket(shipSprite.getX() + shipSprite.getWidth()*1.5f- 15,shipSprite.getY()-20);
        bullets.add(rocket);

    }
    private void createEnemy(){
        Enemy enemy = new Rock();
        enemies.add(enemy);
    }
    private float[] scaledEntityParameters(Entity entity){
        float[] arr = new float[4];
        arr[0] = entity.sprite.getX() + entity.sprite.getWidth()*Math.abs((1f-entity.scale))/2;
        arr[1] = entity.sprite.getY() + entity.sprite.getHeight()*Math.abs((1f-entity.scale))/2;
        arr[2] = entity.width;
        arr[3] = entity.height;
        return arr;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }


}
