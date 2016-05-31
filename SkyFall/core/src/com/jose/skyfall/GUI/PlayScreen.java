package com.jose.skyfall.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jose.skyfall.Logic.*;

public class PlayScreen implements Screen {

    private SkyFall game;
    Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    /*private SkyFall game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    //Classes desenvolvidas
    private Hud hud;
    private Hero hero;
    private Background background;*/

    public PlayScreen(SkyFall game){
        this.game=game;
        //texture=new Texture("badlogic.jpg");
        this.gameCam=new OrthographicCamera();
        this.gamePort=new FillViewport(Gdx.app.getGraphics().getWidth(),Gdx.app.getGraphics().getHeight());

        mapLoader=new TmxMapLoader();
        map=mapLoader.load("world1.tmx");
        gameCam.setToOrtho(false,Gdx.app.getGraphics().getWidth()*2,Gdx.app.getGraphics().getHeight()*2);
        renderer=new OrthogonalTiledMapRenderer(map,1);
        int map_width = (int)(((TiledMapTileLayer)map.getLayers().get(0)).getWidth() *  ((TiledMapTileLayer)map.getLayers().get(0)).getTileWidth());
        int map_height = (int)(((TiledMapTileLayer)map.getLayers().get(0)).getHeight() *  ((TiledMapTileLayer)map.getLayers().get(0)).getTileHeight());
        gameCam.position.set(map_width/2, map_height-20,0);

       /* gameCam=new OrthographicCamera();
        gamePort=new FillViewport(SkyFall.V_WIDTH/SkyFall.PPM,SkyFall.V_HEIGHT/SkyFall.PPM,gameCam); //FitViewport reposiciona e redimensiona o jogo de acordo com o tamanho do ecrã
        hud=new Hud(game.batch);

        background=new Background();

        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        hero = new Hero(540, 1800);*/
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta){
        if(Gdx.input.isTouched())
            gameCam.position.y-=100*delta;
    }

    public void update(float delta){
        handleInput(delta);
        gameCam.update();
        renderer.setView(gameCam);

        /*handleInput(delta);
        //update do background
        gameCam.position.y-=100*delta;
        //Update do heroi
        hero.update(delta);
        //update da cam do jogo
        gameCam.update();
        //update background
        background.update(gameCam);
        //renderer.setView(gameCam);//Apenas mostra o que está na gameCam*/
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        //update das variáveis antes de as desenhar
       /* update(delta);
       //Preenche o ecrã a preto
        Gdx.gl.glClearColor(0,0,0,1);
        //Limpar o ecrã(buffer)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render do background
        background.render();
        //Definir o que vai aparecer do hud
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); //Definir o que aparece no ecra (a partir daqui)

        //Desenhar o heroi
        hero.render(game.batch);
        //hud.stage.draw();*/
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
