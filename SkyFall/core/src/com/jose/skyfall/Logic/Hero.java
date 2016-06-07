package com.jose.skyfall.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.jose.skyfall.Sprites.Animation;


/**
 * Created by Bruno on 09/05/2016.
 */
public class Hero {
    /** Y hero initial velocity */
    private float MOVEMENT = -300;
    /** Hero's width */
    private static final int PLAYER_WIDTH=60;
    /** Hero's height */
    private static final int PLAYER_HEIGHT=70;
    /** Hero's position */
    private Vector3 position;
    /** HEro's velocity */
    private Vector3 velocity;
    /** Hero's texture */
    private Texture texture;
    /** Hero's animation texture */
    private Texture textureAnimation;
    /** Hero's animation texture when he have the super power */
    private Texture textureSuperPowerAnimation;
    /** Hero's Rectangle bounds for collisions*/
    private Rectangle bounds;
    /** Heros' animation to walk */
    private Animation heroAnimation;
    /** Heros' animation to walk with super power */
    private Animation superPowerAnimation;
    /** Hero's States */
    public enum HeroState{NORMAL, SUPER_POWER};
    /** Actual hero state */
    private HeroState actualState;

    /**
     * Hero constructor
     * @param tiledWidth Map's width
     * @param tiledHeight Map's height
     */
    public Hero(int tiledWidth,int tiledHeight,int world){

        Gdx.app.log("OLA","WORLD "+world);
        texture = new Texture("player"+world+".png");
        float x=tiledWidth/2-texture.getWidth()/2;
        int y=tiledHeight-texture.getHeight()-100; //screenHeight/2-texture.getHeight()/2;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bounds = new Rectangle(x, y, texture.getWidth()/3,texture.getHeight());

        textureAnimation=new Texture("player"+world+".png");
        heroAnimation=new Animation(new TextureRegion(textureAnimation),3,0.5f);

        textureSuperPowerAnimation = new Texture("greenPlayerBubble.png");
        superPowerAnimation = new Animation(new TextureRegion(textureSuperPowerAnimation), 3,0.5f);

        //TODO
        actualState = HeroState.NORMAL;
    }

    /**
     * Update the values of the Hero
     * @param delta time variation
     */
    public void update(float delta){

        if(actualState == HeroState.NORMAL)
            heroAnimation.update(delta);
        else
            superPowerAnimation.update(delta);

        updateVolocity();

        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)){
            float accelX = Gdx.input.getAccelerometerX();
            move(accelX * 10,delta);
        }

        bounds.setPosition(position.x, position.y);
    }

    /** Draws the hero */
    public void render(SpriteBatch batch){
        batch.begin();
        if(actualState == HeroState.NORMAL)batch.draw(heroAnimation.getCurrFrame(),getPosition().x,getPosition().y,PLAYER_WIDTH,PLAYER_HEIGHT);

        else
            batch.draw(superPowerAnimation.getCurrFrame(),getPosition().x,getPosition().y,80,80);
        batch.end();
    }

    /**
     * Returns hero's position
     * @return
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Moves the hero
     * @param x position int x axis
     * @param delta time variation
     */
    public void move (float x,float delta){

        velocity.x = -x;
        if (position.x + velocity.x > 62 && position.x + velocity.x+texture.getWidth() <1054)
            position.add(velocity.x, 0, 0);

        position.add(0, MOVEMENT * delta , 0);
    }

    /**
     * Returns the bounds of hero's texture
     * @return hero's bounds
     */
    public Rectangle getBounds(){
        return bounds;
    }

    /**
     * Disposes the animation of the hero
     */
    public void dispose(){
        textureAnimation.dispose();
        textureSuperPowerAnimation.dispose();
    }

    /**
     * updates the heros velocity
     */
    public void updateVolocity(){
        MOVEMENT -= 0.4f;
    }

    /**
     * Returns if hero gets the super power
     * @return true if hero gets the super power
     */
    public boolean getSuperPower(){
        return (actualState == HeroState.SUPER_POWER);
    }

    /**
     * Defines if hero gets the super power
     * @param value true if he gets, false if not
     */
    public void setSuperPower(boolean value){
        if (value == true)
            actualState = HeroState.SUPER_POWER;
        else
            actualState = HeroState.NORMAL;
    }
}
