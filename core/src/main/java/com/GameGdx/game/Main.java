package com.GameGdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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


    @Override
    public void create() {
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

        for(Bullet bullet : bullets) {
            bullet.sprite.draw(batch);
        }
        for(Enemy enemy : enemies) {
            enemy.sprite.draw(batch);
        }
        for(int i = pendingAnimations.size()-1; i >=0 ;i--){

            Entity entity = pendingAnimations.get(i);
            if(entity instanceof Enemy) {
                entity.animationTimer += Gdx.graphics.getDeltaTime();
                Animation<TextureRegion> current = entity.animation;
                batch.draw(current.getKeyFrame(entity.animationTimer), entity.sprite.getX() - 50 + entity.sprite.getWidth() * (1 - entity.sprite.getScaleX()) / 2,
                    entity.sprite.getY() - 19 + entity.sprite.getHeight() * (1 - entity.sprite.getScaleY()) / 2, 179, 114);
                if (current.getKeyFrameIndex(entity.animationTimer) == 2) {
                    pendingAnimations.remove(i);
                }
            }
            else if(entity instanceof Explosion){
                entity.animationTimer += Gdx.graphics.getDeltaTime();
                Animation<TextureRegion> current = entity.animation;
                batch.draw(current.getKeyFrame(entity.animationTimer), entity.sprite.getX()  + entity.width * (1 - entity.sprite.getScaleX()) ,
                    entity.sprite.getY()  + entity.height * (1 - entity.sprite.getScaleY()), entity.width*entity.sprite.getScaleX(), entity.height*entity.sprite.getScaleX());
//                System.out.println(entity.width*entity.sprite.getScaleX() + " " + entity.height*entity.sprite.getScaleX());
                entity.rectangle.set(entity.sprite.getX() - entity.width* entity.sprite.getScaleX()/2 ,
                    entity.sprite.getY() - entity.height*entity.sprite.getScaleY()/2 ,entity.width*entity.sprite.getScaleY(),entity.height*entity.sprite.getScaleY());

                if (current.getKeyFrameIndex(entity.animationTimer) == 4) {
                    explosionList.remove(entity);
                    pendingAnimations.remove(i);
                }

            }
        }
        //
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);  // Use Filled for a solid rectangle or Line for an outline
//        shapeRenderer.setColor(Color.RED);
//        for(Rectangle bulletRectangle: bulletRectangleList)
//            shapeRenderer.rect(bulletRectangle.getX(), bulletRectangle.getY(), bulletRectangle.width, bulletRectangle.height);  // Position and dimensions of the rectangle
//        for(Rectangle enemyRectangle: enemyRectangleList)
//            shapeRenderer.rect(enemyRectangle.getX(), enemyRectangle.getY(), enemyRectangle.getWidth(), enemyRectangle.getHeight());  // Position and dimensions of the rectangle
//
//        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);  // Use Line type to draw rectangles

        for (Entity entity : pendingAnimations) {
//            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(entity.rectangle.getX(), entity.rectangle.getY(), entity.rectangle.width, entity.rectangle.height);
        }

        shapeRenderer.end();

        batch.end();
    }
    private void logic() {
        float delta = Gdx.graphics.getDeltaTime();

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.sprite.translateY(1000f * delta);

            bullet.rectangle.set(bullet.sprite.getX() + (bullet.sprite.getWidth() - bullet.sprite.getWidth() * bullet.sprite.getScaleX())/2,
                bullet.sprite.getY() + (bullet.sprite.getHeight() -bullet.sprite.getHeight()*bullet.sprite.getScaleY())/2,bullet.sprite.getWidth() * bullet.sprite.getScaleX(),bullet.sprite.getHeight()*bullet.sprite.getScaleY());

            for (int j = enemies.size() - 1; j >= 0; j--) {
                Enemy enemy = enemies.get(j);
                if (enemy.rectangle.overlaps(bullet.rectangle)) {
                    if(bullet instanceof Rocket) {
                        Explosion explosion = new Explosion(bullet.sprite.getX(), bullet.sprite.getY());
                        explosionSound.play();
                        pendingAnimations.add(explosion);
                        explosionList.add(explosion);
                    }

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

                    garbageCollector.add(enemies.get(i));
                }
            }
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            if(enemy instanceof Rock)
             enemy.sprite.translateY(-Rock.moveSpeed* delta);
            enemy.rectangle.set(enemy.sprite.getX()+ enemy.sprite.getWidth()*(1-enemy.sprite.getScaleX())/2 ,
                enemy.sprite.getY() + enemy.sprite.getHeight()*(1-enemy.sprite.getScaleY())/2,enemy.sprite.getWidth()*enemy.scale,enemy.sprite.getHeight()* enemy.scale);

            if(enemy.sprite.getY() < 0)
                garbageCollector.add(enemies.get(i));
        }
        for (Entity entity : garbageCollector){
            if(entity instanceof Bullet)
                bullets.remove(entity);
            else if(entity instanceof Enemy)
                enemies.remove(entity);

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
