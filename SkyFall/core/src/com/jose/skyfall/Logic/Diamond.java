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

    private static final int VARIATION = 859;
    private int XMOVEMENT = 100;
    private boolean movX;
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;
    private Random rand;

    private boolean catched;

    public Diamond(float y){
        image = new Texture("diamondPoint.png");
        rand = new Random();

        position = new Vector2(rand.nextInt(VARIATION), y);

        Random mov = new Random();
        int num = mov.nextInt(5);
        movX = (num == 0);

        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());

        catched = false;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(image, position.x, position.y);
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

        catched = false;
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

    public void setCatched(boolean catched){
        this.catched=catched;
    }

    public boolean wasCatched(){
        return catched;
    }
}
