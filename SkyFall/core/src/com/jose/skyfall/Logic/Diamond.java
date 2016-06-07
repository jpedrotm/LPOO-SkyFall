package com.jose.skyfall.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Jose on 05/06/2016.
 */
public class Diamond {

    /** X variaton to the Random */
    private static final int VARIATION = 859;
    /** If they have movement, represents the velocity of the movement */
    private int XMOVEMENT = 100;
    /** Distance to the left side of the screen */
    private int DIAMOND_LEFT_DISTANCE=62;

    /** Moviment int the x axis */
    private boolean movX;
    /** Diamond texture */
    private Texture image;
    /** Position of the diamond */
    private Vector2 position;
    /** Rectangle bounds to verificate the collision */
    private Rectangle bounds;
    /** random variable to randomize the x position of the diamond */
    private Random rand;
    /** True if diamond was catched */
    private boolean catched;

    /**
     * Diamond's constructor
     * @param y Position int the y axis
     */
    public Diamond(float y){
        image = new Texture("diamondPoint.png");
        rand = new Random();

        position = new Vector2(rand.nextInt(VARIATION)+DIAMOND_LEFT_DISTANCE, y);

        Random mov = new Random();
        int num = mov.nextInt(5);
        movX = (num == 0);

        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());

        catched = false;
    }

    /**
     * Draws the visible diamond
     * @param batch batch were diamond is going to be drawn
     */
    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(image, position.x, position.y);
        batch.end();
    }

    /**
     * Returns the diamond position
     * @return vector with diamond position
     */
    public Vector2 getPosition(){ return position; }

    /**
     * Returns the texture used by the diamond
     * @return diamond's image
     */
    public Texture getImage(){ return image; }

    /**
     * When the diamond get out of the screen or get catched, it's repositioned
     * @param y new position in the y axis
     */
    public void reposition(float y){
        position.set(rand.nextInt(VARIATION), y);
        bounds.setPosition(position.x,position.y);

        Random mov = new Random();
        int num = mov.nextInt(5);
        movX = (num == 0);

        catched = false;
    }

    /**
     * Returns true if the diamonds colides with hero
     * @param hero bounds of the hero
     * @return TRue if collides, false if not
     */
    public boolean collides(Rectangle hero){
        return hero.overlaps(bounds);
    }

    /**
     * disposes the diamond image
     */
    public void dispose(){

        image.dispose();
    }

    /**
     * If the diamond had movement, it is going to be updated
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
     * defines if the diamond was catched
     * @param catched true if was catched
     */
    public void setCatched(boolean catched){
        this.catched=catched;
    }

    /**
     * Returns true if diamond was catched
     * @return true if was catched
     */
    public boolean wasCatched(){
        return catched;
    }

    /**
     * Set's the boolean value of the movement
     * @param val boolean value of the movement
     */
    public void setMovX(boolean val){
        movX=true;
    }


}