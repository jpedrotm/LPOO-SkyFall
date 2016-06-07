package com.jose.skyfall.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jose.skyfall.States.MenuScreen;

public class SkyFall extends Game {

	public static final int V_WIDTH=1054;
	public static final int V_HEIGHT=1900;
	public SpriteBatch batch;

	private int world=1;
	private boolean isMusicOn;

	@Override
	public void create () {
		batch = new SpriteBatch();
		isMusicOn=true;
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	public int getWorld(){
		return world;
	}

	public void setWorld(int world){
		this.world=world;
	}

	public void setIsMusicOn(boolean val){
		isMusicOn=val;
	}

	public boolean getIsMusicOn(){
		return isMusicOn;
	}
}
