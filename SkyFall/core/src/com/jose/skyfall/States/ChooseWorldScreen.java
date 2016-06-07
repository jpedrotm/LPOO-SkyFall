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
import com.jose.skyfall.Game.SkyFall;

/**
 * Created by Jose on 02/06/2016.
 */
public class ChooseWorldScreen implements Screen {

    private static final int ADJUST_WORLD1IMAGE_HEIGHT=60;
    private static final int ADJUST_WORLD2IMAGE_HEIGHT=-60;
    private static final int ADJUST_WORLD3IMAGE_HEIGHT=-180;

    private SkyFall game;
    private OrthographicCamera chooseWorldCam;
    private Viewport chooseWorldPort;

    private ImageButton worldButton1;
    private ImageButton worldButton2;
    private ImageButton worldButton3;
    private ImageButton backButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private Texture background;
    private Stage stage;
    private float width;
    private float height;

    public ChooseWorldScreen(SkyFall game)
    {
        this.game=game;
        background = new Texture("backgroundChooseWorld.png");
        width = background.getWidth();
        height = background.getHeight();
        chooseWorldCam=new OrthographicCamera();
        chooseWorldPort = new StretchViewport(background.getWidth(),background.getHeight(),chooseWorldCam);
        stage = new Stage(chooseWorldPort);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        ButtonsPack = new TextureAtlas("ChooseWorldButtons.pack");
        skin = new Skin();
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("worldImage1");
        style.down = skin.getDrawable("worldImage1");
        worldButton1 = new ImageButton(style);
        worldButton1.setPosition(width/2-worldButton1.getWidth()/2-40,height/2+ADJUST_WORLD1IMAGE_HEIGHT);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("worldImage2");
        style.down = skin.getDrawable("worldImage2");
        worldButton2 = new ImageButton(style);
        worldButton2.setPosition(width/2-worldButton2.getWidth()/2+40,height/2+ADJUST_WORLD2IMAGE_HEIGHT);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("worldImage3");
        style.down = skin.getDrawable("worldImage3");
        worldButton3 = new ImageButton(style);
        worldButton3.setPosition(width/2-worldButton3.getWidth()/2-40, height/2+ADJUST_WORLD3IMAGE_HEIGHT);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("homeButton");
        style.down = skin.getDrawable("homeButtonPressed");
        backButton = new ImageButton(style);
        backButton.setPosition(20,height-80);


        stage.addActor(worldButton1);
        stage.addActor(worldButton2);
        stage.addActor(worldButton3);
        stage.addActor(backButton);

        loadListeneres();
    }

    public void loadListeneres(){
        worldButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setWorld(1);
                Screen screen = new PlayScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });

        worldButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setWorld(2);
                Screen screen = new PlayScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });

        worldButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setWorld(3);
                Screen screen = new PlayScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new MenuScreen(game);
                game.setScreen(screen);
                dispose();
            }
        });
    }

    @Override
    public void show() {

    }

    public void handleInput(float delta){

    }
    public void update(float delta){
        handleInput(delta);
    }


    @Override
    public void render(float delta) {

        update(delta);

        chooseWorldCam.update();
        game.batch.setProjectionMatrix(chooseWorldCam.combined);
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
