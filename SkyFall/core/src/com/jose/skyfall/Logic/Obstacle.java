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
    /** Variation of the random value in the x axis */
    private static final int VARIATION = 859;
    /** Velocity in the x axis  */
    private int XMOVEMENT = 100;
    /** Movement in the x axis */
    private boolean movX;
    /** Obstacle texture */
    private Texture image;
    /** Explosion texture */
    private Texture textureExplosion;
    /** Obstacle position */
    private Vector2 position;
    /** Obstacle textures bounds */
    private Rectangle bounds;
    /** Random value to create the position in the x axis */
    private Random rand;
    /** Animation of obstacle exploding */
    private Animation explosionAnimation;
    /** Obstacle destroied */
    private boolean destroied;

    /**
     * Obstacle constructor
     * @param y position in the y axis
     */
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

    /**
     * Draws the visible obstacle
     * @param batch Batch were obstacle is going to be drawn
     */
    public void render(SpriteBatch batch){
        batch.begin();
        if (!destroied)
            batch.draw(image, position.x, position.y);
        else
            batch.draw(explosionAnimation.getCurrFrame(),getPosition().x,getPosition().y, 118 ,118);
        batch.end();
    }

    /**
     * Returns the obstacle position
     * @return Vector of obstacle's position
     */
    public Vector2 getPosition(){ return position; }

    /**
     * Returns the Obstacle texture
     * @return Obstacle texture
     */
    public Texture getImage(){ return image; }

    /**
     * When the obstacle went out of the screen, it will be repositioned
     * @param y new position in the y axis
     */
    public void reposition(float y){
        position.set(rand.nextInt(VARIATION), y);
        bounds.setPosition(position.x,position.y);

        Random mov = new Random();
        int num = mov.nextInt(5);
        movX = (num == 0);

        destroied = false;
    }

    /**
     * Returns the collision with the hero
     * @param hero Hero bounds
     * @return True if collides, false if not
     */
    public boolean collides(Rectangle hero){
        return hero.overlaps(bounds);
    }

    /**
     * Disposes the obstacle texture
     */
    public void dispose(){

        image.dispose();
        textureExplosion.dispose();
    }

    /**
     * If obstacle had movement, updates the position
     * @param delta time variation
     */
    public void updatePosition(float delta){
        if (movX){
            position.add(XMOVEMENT * delta, 0);
            bounds.setPosition(position.x, position.y);
        }

        if (position.x < 0 || position.x > 1024 - image.getWidth())
            XMOVEMENT = -XMOVEMENT;
    }

    /**
     * destroys the obstacle
     */
    public void destroy(){
        destroied = true;
        movX = false;
    }

    /**
     * Returns true if the obstacle is destroyed
     * @return
     */
    public boolean isDestroied(){
        return destroied;
    }

    /**
     * Updates the explosion animation
     * @param delta time variation
     */
    public void update(float delta){
        if(destroied)
            explosionAnimation.update(delta);
    }

    /**
     * Defines the movement in the x axis
     * @param val true or false
     */
    public void setMovX(boolean val){
        movX=true;
    }
}
