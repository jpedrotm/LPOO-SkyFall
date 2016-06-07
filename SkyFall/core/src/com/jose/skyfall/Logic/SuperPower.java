package com.jose.skyfall.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Bruno on 03/06/2016.
 */
public class SuperPower {
    /** Variation of the random value in the x axis */
    private static final int VARIATION = 859;
    /** Texture of the super power */
    private Texture image;
    /** super power position */
    private Vector2 position;
    /** Super power texture bounds */
    private Rectangle bounds;
    /** Random value to the position in the x axis */
    private Random rand;
    /** Super Power catched */
    private boolean catched;

    /**
     * Super Power constructor
     * @param y Position in the y axis
     */
    public SuperPower(float y){
        image = new Texture("bubble.png");
        rand = new Random();
        position = new Vector2(rand.nextInt(VARIATION), y);
        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());
        catched = false;
    }

    /**
     * Returns the super power position
     * @return vector with the super power position
     */
    public Vector2 getPosition(){ return position; }

    /**
     * Returns the texture of super power
     * @return Super Power's texture
     */
    public Texture getImage(){ return image; }

    /**
     * Returns the collision with hero
     * @param hero Hero bounds
     * @return True if hero collides with the super power, false if not
     */
    public boolean collides(Rectangle hero){
        return hero.overlaps(bounds);
    }

    /**
     * Repositionate the super power if went out of the screen or get caught
     * @param y position in the y axis
     */
    public void reposition(float y){
        position.set(rand.nextInt(VARIATION), y);
        bounds.setPosition(position.x,position.y);
    }

    /**
     * Returns if super power was catched
     * @return catched value
     */
    public boolean getCathed(){
        return catched;
    }

    /**
     * Defines if super power was catched
     * @param value true if super power was caught, false if not
     */
    public void setCatched(boolean value){

        catched = value;
    }

    /**
     * disposes the super power texture
     */
    public void dispose(){

        image.dispose();
    }
}
