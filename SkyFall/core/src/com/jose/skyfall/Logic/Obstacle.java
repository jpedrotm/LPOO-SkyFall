package com.jose.skyfall.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jose.skyfall.Sprites.Animation;

import java.util.Random;

/**
 * Created by Bruno on 31/05/2016.
 */
public class Obstacle {
    private static final int VARIATION = 859;
    private int XMOVEMENT = 100;
    private boolean movX;
    private Texture image;
    private Texture textureExplosion;
    private Vector2 position;
    private Rectangle bounds;
    private Random rand;
    private Animation explosionAnimation;

    private boolean destroied;

    public Obstacle(float y){
        image = new Texture("obstaclesBomb.png");
        rand = new Random();

        position = new Vector2(rand.nextInt(VARIATION), y);

        Random mov = new Random();
        int num = mov.nextInt(5);
        movX = (num == 0);

        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());

        textureExplosion = new Texture("Explosion-Sprite-Sheet.png");
        explosionAnimation = new Animation(new TextureRegion(textureExplosion),5,0.6f);

        destroied = false;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        if (!destroied)
            batch.draw(image, position.x, position.y);
        else
            batch.draw(explosionAnimation.getCurrFrame(),getPosition().x,getPosition().y, 118 ,118);
        batch.end();
    }

    public Vector2 getPosition(){ return position; }
    public Texture getImage(){ return image; }

    public void reposition(float y){
        position.set(rand.nextInt(VARIATION), y);
        bounds.setPosition(position.x,position.y);

        Random mov = new Random();
        int num = mov.nextInt(5);
        movX = (num == 0);

        destroied = false;
    }

    public boolean collides(Rectangle hero){
        return hero.overlaps(bounds);
    }

    public void dispose(){
        image.dispose();
    }

    public void updatePosition(float delta){
        if (movX){
            position.add(XMOVEMENT * delta, 0);
            bounds.setPosition(position.x, position.y);
        }

        if (position.x < 0 || position.x > 1024 - image.getWidth())
            XMOVEMENT = -XMOVEMENT;
    }

    public void destroy(){
        destroied = true;
        movX = false;
    }

    public boolean isDestroied(){
        return destroied;
    }

    public void update(float delta){
        if(destroied)
            explosionAnimation.update(delta);
    }

    public void setMovX(boolean val){
        movX=true;
    }
}
