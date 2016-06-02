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
    private static final int VARIATION = 913;
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;
    private Random rand;

    public Obstacle(float y){
        image = new Texture("obstacleBomb.png");
        rand = new Random();

        position = new Vector2(rand.nextInt(VARIATION), y);

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
    }

    public boolean collides(Rectangle hero){
        return hero.overlaps(bounds);
    }
}
