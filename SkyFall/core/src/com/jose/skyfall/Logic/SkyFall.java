package com.jose.skyfall.Logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jose.skyfall.GUI.MenuScreen;
import com.jose.skyfall.GUI.PlayScreen;

import java.util.Stack;

public class SkyFall extends Game {

	public static final int V_WIDTH=1024;
	public static final int V_HEIGHT=1680;
	public SpriteBatch batch;

	public Stack<Screen> screens = new Stack<Screen>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		Screen screen = new MenuScreen(this);
		screens.push(screen);
		setScreen(screens.peek());
	}

	@Override
	public void render () {
		super.render();
	}
}
