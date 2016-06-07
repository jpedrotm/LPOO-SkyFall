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
    private static final int VARIATION = 859;
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;
    private Random rand;
    private boolean catched;

    public SuperPower(float y){
        image = new Texture("bubble.png");
        rand = new Random();
        position = new Vector2(rand.nextInt(VARIATION), y);
        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());
        catched = false;
    }

    public Vector2 getPosition(){ return position; }
    public Texture getImage(){ return image; }

    public boolean collides(Rectangle hero){
        return hero.overlaps(bounds);
    }

    public void reposition(float y){
        position.set(rand.nextInt(VARIATION), y);
        bounds.setPosition(position.x,position.y);
    }

    public boolean getCathed(){
        return catched;
    }

    public void setCatched(boolean value){

        catched = value;
    }

    public void dispose(){
        image.dispose();
    }
}
