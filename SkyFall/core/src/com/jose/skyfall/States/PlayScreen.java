package com.jose.skyfall.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.jose.skyfall.Logic.Diamond;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jose.skyfall.Logic.Background;
import com.jose.skyfall.Logic.Hero;
import com.jose.skyfall.Logic.HighScores;
import com.jose.skyfall.Logic.Obstacle;
import com.jose.skyfall.Logic.SkyFall;
import com.jose.skyfall.Logic.SuperPower;
import com.jose.skyfall.Screens.Hud;


public class PlayScreen implements Screen {
    private static final int OBSTACLE_SPACING = 350;
    private static final int OBSTACLES_COUNT = 6;
    private static final int OBSTACLES_INICIAL_DISTANCE=600;

    private static final int SUPERPOWER_SPACING = 6000;
    private static final int SUPERPOWER_IINTIAL_DISTANCE = 2000;

    private static final int INCREASE_SCORE_BY_TIME=1;
    private static final int INCREASE_SCORE_BY_DIAMOND=20;

    private static final int DIAMONDS_COUNT=4;
    private static final int DIAMONDS_SPACING = 1500;

    private SkyFall game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private Hero hero;
    private Array<Obstacle> obstacles;
    private Array<Diamond> diamonds;
    private SuperPower sp;
    private Background background;
    private HighScores highScores;



    public PlayScreen(SkyFall game){
        this.game=game;
        gameCam=new OrthographicCamera();
        gamePort=new FitViewport(SkyFall.V_WIDTH,SkyFall.V_HEIGHT,gameCam); //FitViewport reposiciona e redimensiona o jogo de acordo com o tamanho do ecrã

        highScores=new HighScores(game.getWorld());

        hud=new Hud(game.batch,highScores.getScore());

        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        Gdx.app.log("OLA","OLA"+game.getWorld());
        background=new Background("world"+game.getWorld()+".tmx");

        hero = new Hero(background.getTiledWidth(),background.getTiledHeight());
        obstacles = new Array<Obstacle>();

        Gdx.app.log("OLA","OLA"+background.getTiledHeight());

        for (int i = 1; i <= OBSTACLES_COUNT; i++){
            obstacles.add(new Obstacle(background.getTiledHeight()-OBSTACLES_INICIAL_DISTANCE-i*OBSTACLE_SPACING));
    }

        diamonds=new Array<Diamond>();

        for(int i=1;i<=DIAMONDS_COUNT;i++) {
            diamonds.add(new Diamond(background.getTiledHeight()-DIAMONDS_SPACING*i));
        }

        sp = new SuperPower(SUPERPOWER_SPACING + SUPERPOWER_IINTIAL_DISTANCE);
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta){

    }

    public void update(float delta){
        handleInput(delta);

        hero.update(delta);
        highScores.update(INCREASE_SCORE_BY_TIME);
        hud.update(delta,highScores.getScore());
        gameCam.position.y = hero.getPosition().y - (gameCam.viewportHeight/2)+200;

        if((gameCam.position.y + (gameCam.viewportHeight/2) < sp.getPosition().y) || sp.getCathed())
            sp.reposition(sp.getPosition().y - SUPERPOWER_SPACING);

        if(sp.collides(hero.getBounds())){
            sp.setCatched(true);
            hero.setSuperPower(true);
        }

        //Obstacles----------------------------------------------------------------------------------------
        for (Obstacle obs : obstacles){
            obs.update(delta);
            obs.updatePosition(delta);

            if(gameCam.position.y + (gameCam.viewportHeight/2) < obs.getPosition().y)
                obs.reposition(obs.getPosition().y - (OBSTACLE_SPACING * OBSTACLES_COUNT));

            if(obs.collides(hero.getBounds()) && !obs.isDestroied()) {
                if (hero.getSuperPower()){
                    hero.setSuperPower(false);
                    sp.setCatched(false);
                    obs.destroy();
                }
                else{
                    highScores.saveScore();
                    game.setScreen(new ChooseWorldScreen(game));
                    dispose();
                    break;
                }
            }
        }

        //Diamonds---------------------------------------------------------------------

        for (Diamond diamond : diamonds){
            diamond.updatePosition(delta);

            if(gameCam.position.y + (gameCam.viewportHeight/2) < diamond.getPosition().y)
                diamond.reposition(diamond.getPosition().y - (DIAMONDS_SPACING * DIAMONDS_COUNT));

            if(diamond.collides(hero.getBounds())) {
                highScores.update(INCREASE_SCORE_BY_DIAMOND);
                diamond.setCatched(true);
                if(game.getIsMusicOn())
                    SkyFall.manager.get("audio/diamondCatchedSound.wav",Sound.class).play();
            }
        }

        gameCam.update();
        background.update(gameCam);
    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        background.render();

        game.batch.setProjectionMatrix(gameCam.combined);

        hero.render(game.batch);

        game.batch.begin();
        game.batch.draw(sp.getImage(), sp.getPosition().x, sp.getPosition().y);
        for (Diamond diamond : diamonds){
            if(!diamond.wasCatched())
                game.batch.draw(diamond.getImage(),diamond.getPosition().x,diamond.getPosition().y);
            }
        game.batch.end();
        for (Obstacle obstacle : obstacles){
            obstacle.render(game.batch);
        }

        hud.stage.draw();
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

        hero.dispose();
        background.dispose();
        for(Diamond ds : diamonds)
        {
            ds.dispose();
        }
        for(Obstacle obs: obstacles)
        {
            obs.dispose();
        }
        sp.dispose();
    }
}
