package com.jose.skyfall.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jose.skyfall.Sprites.Animation;

import java.util.Random;

public class Enemie {

    /** Enemie texture width.*/
    private static final int ENEMIE_WIDTH=50;
    /** Enemie texture height. */
    private static final int ENEMIE_HEIGHT=55;

    /** Variation of the random value in the x axis */
    private static final int VARIATION = 859;
    /** Velocity in the x axis  */
    private static final int SPEED_Y=250;
    /** Obstacle texture */
    private Texture image;
    /** Explosion texture */
    private Texture textureExplosion;
    /** Enimie animation textures*/
    private Texture enemieTexture;
    /** Obstacle position */
    private Vector2 position;
    /** Obstacle textures bounds */
    private Rectangle bounds;
    /** Random value to create the position in the x axis */
    private Random rand;
    /** Animation of obstacle exploding */
    private Animation explosionAnimation;
    /**Animation of the enemie moving.*/
    private Animation enemieAnimation;
    /** Enemie destroied */
    private boolean destroied;

    /**
     * Enemie constructor
     * @param y position in the y axis
     */
    public Enemie(float y){
        image = new Texture("enemie.png");
        rand = new Random();

        position = new Vector2(rand.nextInt(VARIATION), y);

        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());

        textureExplosion = new Texture("Explosion-Sprite-Sheet.png");
        explosionAnimation = new Animation(new TextureRegion(textureExplosion),5,0.6f);

        enemieTexture=new Texture("enemieImages.png");
        enemieAnimation=new Animation(new TextureRegion(enemieTexture),3,0.4f);



        destroied = false;
    }

    /**
     * Draws the visible enemie.
     * @param batch Batch were obstacle is going to be drawn.
     */
    public void render(SpriteBatch batch){
        batch.begin();
        if (!destroied)
            batch.draw(enemieAnimation.getCurrFrame(),getPosition().x,getPosition().y,ENEMIE_WIDTH,ENEMIE_HEIGHT);
        else
            batch.draw(explosionAnimation.getCurrFrame(),getPosition().x,getPosition().y, 118 ,118);
        batch.end();
    }

    /**
     * Returns the enemie position.
     * @return Vector of obstacle's position.
     */
    public Vector2 getPosition(){ return position; }

    /**
     * Returns the enemie texture.
     * @return Enemie texture.
     */
    public Texture getImage(){ return image; }

    /**
     * When the enemie went out of the screen, it will be repositioned.
     * @param y new position in the y axis.
     */
    public void reposition(float y){
        position.set(rand.nextInt(VARIATION), y);
        bounds.setPosition(position.x,position.y);

        destroied = false;
    }

    /**
     * Returns the collision with the hero.
     * @param hero Hero bounds.
     * @return True if collides, false if not.
     */
    public boolean collides(Rectangle hero){
        return hero.overlaps(bounds);
    }

    /**
     * Disposes the enemie texture.
     */
    public void dispose(){
        image.dispose();
    }

    /**
     * If enemie had movement, updates the position.
     * @param delta time variation.
     */
    public void updatePosition(float delta){

        position.add(0,SPEED_Y * delta);
        bounds.setPosition(position.x, position.y);

    }

    /**
     * destroys the enemie.
     */
    public void destroy(){
        destroied = true;
    }

    /**
     * Returns true if the enemie is destroyed.
     * @return true if the enemie was destroyed, false otherwise.
     */
    public boolean isDestroied(){
        return destroied;
    }

    /**
     * Updates the explosion animation.
     * @param delta time variation.
     */
    public void update(float delta){
        if(destroied)
            explosionAnimation.update(delta);

        enemieAnimation.update(delta);
    }
}
