package com.jose.skyfall.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jose.skyfall.Logic.SkyFall;

public class PlayScreen implements Screen {

    private SkyFall game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Hero hero;
    AndroidApplicationConfiguration config;



    public PlayScreen(SkyFall game){
        this.game=game;
        gameCam=new OrthographicCamera();
        gamePort=new FitViewport(SkyFall.V_WIDTH,SkyFall.V_HEIGHT,gameCam); //FitViewport reposiciona e redimensiona o jogo de acordo com o tamanho do ecrã
        hud=new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map=mapLoader.load("test.tmx");
        renderer= new OrthogonalTiledMapRenderer(map);

        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        hero = new Hero(450, 1500);

    }

    @Override
    public void show() {

    }

    public void handleInput(float delta){
    }

    public void update(float delta){
        handleInput(delta);

        gameCam.position.y-=100*delta;


        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope)){
            float gyroX = Gdx.input.getGyroscopeX();
            float gyroY = Gdx.input.getGyroscopeY();
            float gyroZ = Gdx.input.getGyroscopeZ();
            hero.move(gyroY * 50);
        }
        hero.update(delta);

        gameCam.update();
        renderer.setView(gameCam);//Apenas mostra o que está na gameCam
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); //Definir o que aparece no ecra (a partir daqui)

        game.batch.begin();
        game.batch.draw(hero.getTexture(), hero.getPosition().x, hero.getPosition().y);
        game.batch.end();

        //hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) { //sempre que é alterado o tamanho da janela e chamada esta função
        gamePort.update(width,height);
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
