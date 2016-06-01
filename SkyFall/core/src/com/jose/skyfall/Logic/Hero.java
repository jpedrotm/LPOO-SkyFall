package com.jose.skyfall.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import sun.management.Sensor;

/**
 * Created by Bruno on 09/05/2016.
 */
public class Hero {
    private static final int MOVEMENT = -150;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bounds;

    public Hero(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("playB.png");
        bounds = new Rectangle(x, y, texture.getWidth(),texture.getHeight());
    }

    public void update(float delta){

        /*if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope)){
            float gyroY = Gdx.input.getGyroscopeY();
            move(gyroY * 50);
        }*/
        if (position.x + velocity.x > 0 && position.x + velocity.x < 913)
            position.add(velocity.x, 0, 0);
        position.add(0, MOVEMENT * delta , 0);
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(getTexture(),getPosition().x, getPosition().y);
        batch.end();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void move (float x){
        velocity.x = x;
    }

    public Rectangle getBounds(){
        return bounds;
    }
}
