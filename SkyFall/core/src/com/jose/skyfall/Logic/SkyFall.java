package com.jose.skyfall.Logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jose.skyfall.States.MenuScreen;
import com.jose.skyfall.States.SingletonMenu;

public class SkyFall extends Game {
	
	public static final int V_WIDTH=1054;
	public static final int V_HEIGHT=1900;
	public SpriteBatch batch;
	public SingletonMenu.Screens state;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
