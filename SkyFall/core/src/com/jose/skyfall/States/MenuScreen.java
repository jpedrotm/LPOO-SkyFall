package com.jose.skyfall.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jose.skyfall.Logic.SkyFall;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * Created by Jose on 09/05/2016.
 */
public class MenuScreen implements Screen {

    private SkyFall game;
    private OrthographicCamera menuCam;
    private Viewport menuPort;

    private ImageButton playButton;
    private ImageButton highScoresButton;
    private ImageButton exitButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Texture background;
    private Stage stage;
    private float width;
    private float height;

    public MenuScreen(SkyFall game){

        this.game=game;

        background = new Texture("backgroundMenu.png");
        width = background.getWidth();
        height = background.getHeight();
        menuCam=new OrthographicCamera();
        menuPort = new StretchViewport(background.getWidth(),background.getHeight(),menuCam);
        stage = new Stage(menuPort);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        //buttons
        ButtonsPack = new TextureAtlas("MenuButtons.atlas");
        skin = new Skin();
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Play");
        style.down = skin.getDrawable("Play");
        playButton = new ImageButton(style);
        playButton.setPosition(width/2-playButton.getWidth()/2,height/2+80);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Scores");
        style.down = skin.getDrawable("Scores");
        highScoresButton = new ImageButton(style);
        highScoresButton.setPosition(width/2-playButton.getWidth()/2,height/2-20);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Exit");
        style.down = skin.getDrawable("Exit");
        exitButton = new ImageButton(style);
        exitButton.setPosition(width/2-playButton.getWidth()/2, height/2-120);


        stage.addActor(playButton);
        stage.addActor(highScoresButton);
        stage.addActor(exitButton);

    }

    public void handleInput(float delta){

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new ChooseWorldScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });

        highScoresButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new HighScoreScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });
    }

    public void update(float delta){
        handleInput(delta);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        menuCam.update();
        game.batch.setProjectionMatrix(menuCam.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();
        stage.draw();
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
        background.dispose();
        stage.dispose();
        ButtonsPack.dispose();
        skin.dispose();
    }
}
