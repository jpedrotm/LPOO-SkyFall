package com.jose.skyfall.Logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jose.skyfall.GUI.MenuScreen;
import com.jose.skyfall.GUI.PlayScreen;

public class SkyFall extends Game {

	public static final int V_WIDTH=1920;
	public static final int V_HEIGHT=1080;
	public static final int PPM=1;
	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
