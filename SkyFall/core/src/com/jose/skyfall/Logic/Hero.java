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
    private float MOVEMENT = -300;
    private static final int PLAYER_WIDTH=60;
    private static final int PLAYER_HEIGHT=70;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Texture textureAnimation;
    private Texture textureSuperPowerAnimation;
    private Rectangle bounds;
    private Animation heroAnimation;
    private Animation superPowerAnimation;

    public enum HeroState{NORMAL, SUPER_POWER};
    private HeroState actualState;

    public Hero(int tiledWidth,int tiledHeight){

        texture = new Texture("PlayerGreen.png");
        float x=tiledWidth/2-texture.getWidth()/2;
        int y=tiledHeight-texture.getHeight()-100; //screenHeight/2-texture.getHeight()/2;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bounds = new Rectangle(x, y, texture.getWidth(),texture.getHeight());

        textureAnimation=new Texture("PlayerGreen.png");
        heroAnimation=new Animation(new TextureRegion(textureAnimation),3,0.5f);

        textureSuperPowerAnimation = new Texture("greenPlayerBubble.png");
        superPowerAnimation = new Animation(new TextureRegion(textureSuperPowerAnimation), 3,0.5f);

        //TODO
        actualState = HeroState.NORMAL;
    }

    public void update(float delta){

        if(actualState == HeroState.NORMAL)
            heroAnimation.update(delta);
        else
            superPowerAnimation.update(delta);

        updateVolocity();

        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)){
            float accelX = Gdx.input.getAccelerometerX();
            move(accelX * 20,delta);
        }

        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch){
        batch.begin();
        if(actualState == HeroState.NORMAL)
            batch.draw(heroAnimation.getCurrFrame(),getPosition().x,getPosition().y,PLAYER_WIDTH,PLAYER_HEIGHT);
        else
            batch.draw(superPowerAnimation.getCurrFrame(),getPosition().x,getPosition().y,80,80);
        batch.end();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void move (float x,float delta){

        velocity.x = -x;
        if (position.x + velocity.x > 62 && position.x + velocity.x+texture.getWidth() <1054)
            position.add(velocity.x, 0, 0);

        position.add(0, MOVEMENT * delta , 0);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        textureAnimation.dispose();
    }

    public void updateVolocity(){
        MOVEMENT -= 0.4f;
    }

    //TODO
    public boolean getSuperPower(){
        return (actualState == HeroState.SUPER_POWER);
    }

    //TODO
    public void setSuperPower(boolean value){
        if (value == true)
            actualState = HeroState.SUPER_POWER;
        else
            actualState = HeroState.NORMAL;
    }
}
