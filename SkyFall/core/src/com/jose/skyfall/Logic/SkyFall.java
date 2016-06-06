package com.jose.skyfall.Logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jose.skyfall.States.MenuScreen;

public class SkyFall extends Game {

	public static final int V_WIDTH=1054;
	public static final int V_HEIGHT=1900;
	public SpriteBatch batch;
	public static AssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager=new AssetManager();
		manager.load("audio/superPowerSong.ogg",Music.class);
		manager.load("audio/diamondCatchedSound.ogg",Music.class);
		manager.finishLoading();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
