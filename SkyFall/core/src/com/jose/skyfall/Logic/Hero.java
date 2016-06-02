package com.jose.skyfall.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jose.skyfall.Sprites.Animation;

import sun.management.Sensor;

/**
 * Created by Bruno on 09/05/2016.
 */
public class Hero {
    private static final int MOVEMENT = -300;
    private static final int PLAYER_WIDTH=60;
    private static final int PLAYER_HEIGHT=70;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Texture textureAnimation;
    private Rectangle bounds;
    private Animation heroAnimation;

    public Hero(int tiledWidth,int tiledHeight){

        texture = new Texture("PlayerGreen.png");
        float x=tiledWidth/2-texture.getWidth()/2;
        int y=tiledHeight-texture.getHeight()-100; //screenHeight/2-texture.getHeight()/2;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bounds = new Rectangle(x, y, texture.getWidth(),texture.getHeight());

        textureAnimation=new Texture("PlayerGreen.png");
        heroAnimation=new Animation(new TextureRegion(textureAnimation),3,0.5f);
    }

    public void update(float delta){

        heroAnimation.update(delta);
        if (position.x + velocity.x > 0 && position.x + velocity.x < 913)
            position.add(velocity.x, 0, 0);
        position.add(0, MOVEMENT * delta , 0);
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(heroAnimation.getCurrFrame(),getPosition().x,getPosition().y,PLAYER_WIDTH,PLAYER_HEIGHT);
        batch.end();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return heroAnimation.getCurrFrame();
    }

    public void move (float x){
        velocity.x = x;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        textureAnimation.dispose();
    }
}
