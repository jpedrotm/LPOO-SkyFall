package com.jose.skyfall.GUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jose.skyfall.Logic.SkyFall;

import sun.rmi.runtime.Log;

/**
 * Created by Jose on 09/05/2016.
 */
public class MenuScreen implements Screen {

    private Texture playB;
    private SkyFall game;
    private OrthographicCamera gameCam;

    public MenuScreen(SkyFall game){

        playB=new Texture("playB.png");
        gameCam=new OrthographicCamera();
        this.game=game;
    }

    public void handleInput(float delta){
        if(Gdx.input.getX()>((SkyFall.V_WIDTH/2)-(playB.getWidth()/2)) && Gdx.input.getX()<((SkyFall.V_WIDTH/2)+(playB.getWidth()/2)) && Gdx.input.getY()>((SkyFall.V_HEIGHT/2)-(playB.getHeight()/2)) && Gdx.input.getY()<((SkyFall.V_HEIGHT/2)+(playB.getHeight()/2))){
            Screen screen = new PlayScreen(game);
            game.screens.push(screen);
            game.setScreen(game.screens.peek());
        }
    }

    public void update(float delta){
        gameCam.position.set(0, 0, 0);
        handleInput(delta);

        gameCam.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(playB,(SkyFall.V_WIDTH / 2)-(playB.getWidth() / 2),SkyFall.V_HEIGHT / 2);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
