package com.jose.skyfall.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Bruno on 31/05/2016.
 */
public class Obstacle {
    private static final int VARIATION = 859;
    private int XMOVEMENT = 100;
    private boolean movX;
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;
    private Random rand;

    public Obstacle(float y){
        image = new Texture("obstaclesBomb.png");
        rand = new Random();

        position = new Vector2(rand.nextInt(VARIATION), y);

        Random mov = new Random();
        int num = mov.nextInt(5);
        movX = (num == 0);

        bounds = new Rectangle(position.x, position.y, image.getWidth(),image.getHeight());
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
}
