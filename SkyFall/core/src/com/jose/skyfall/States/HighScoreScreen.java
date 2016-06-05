package com.jose.skyfall.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jose.skyfall.Logic.SkyFall;

/**
 * Created by Jose on 02/06/2016.
 */
public class HighScoreScreen implements Screen {

    private SkyFall game;
    private OrthographicCamera highScoreCam;
    private Viewport highScorePort;

    private ImageButton backButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Texture background;
    private Stage stage;
    private float width;
    private float height;

    public HighScoreScreen(SkyFall game)
    {
        this.game=game;
        background = new Texture("backgroundHighScore.png");
        width = background.getWidth();
        height = background.getHeight();
        highScoreCam=new OrthographicCamera();
        highScorePort = new StretchViewport(background.getWidth(),background.getHeight(),highScoreCam);
        stage = new Stage(highScorePort);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        ButtonsPack = new TextureAtlas("ChooseWorldButtons.pack");
        skin = new Skin();
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("BackButton");
        style.down = skin.getDrawable("BackButton");
        backButton = new ImageButton(style);
        backButton.setPosition(20,height-80);

        stage.addActor(backButton);

    }

    @Override
    public void show() {

    }

    public void handleInput(float delta){

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new MenuScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });
    }

    public void update(float delta){
        handleInput(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        highScoreCam.update();
        game.batch.setProjectionMatrix(highScoreCam.combined);
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
        skin.dispose();
        ButtonsPack.dispose();
    }
}
