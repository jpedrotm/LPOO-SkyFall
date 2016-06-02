package com.jose.skyfall.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jose.skyfall.Logic.SkyFall;

/**
 * Created by Jose on 08/05/2016.
 */
public class Hud { //Classe responsável pela representação do score, vidas, status do jogador, tempo, qualquer coisa estática no ecrá

    public Stage stage; //Caixa vazia que recebe de seguida elementos a dispor no ecrã
    public Viewport viewport; //necessário criar um  novo viewport de forma a ter um fixo ao contrário do responsável pela situação de jogo

    private Integer score;
    private Integer worldTimer;
    private float timeCount;

    private Label timeCountLabel;
    private Label scoreLabel;
    private Label worldLabel;
    private Label playerLabel;



    public Hud(SpriteBatch sb){
        worldTimer=300; //a mudar depois TODO
        timeCount=0;
        score=0;
        viewport=new FitViewport(SkyFall.V_WIDTH,SkyFall.V_HEIGHT,new OrthographicCamera());
        stage=new Stage(viewport,sb);

        Table table=new Table(); //Vamos colocar dentro da "stage" de forma a organizar á nosso maneira os layouts
        table.top(); //Por defeito alinha no centro, alinhamos assim no topo do ecrã
        table.setFillParent(true); //Colocar a table do mesmo tamanho que "stage"

        scoreLabel=new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(),Color.WHITE));//"%06d" número de dígitos que vai mostrar. É necessário o String.format devido a ser do tipo Integer
        worldLabel=new Label("World 1",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        playerLabel= new Label("Player Name",new Label.LabelStyle(new BitmapFont(),Color.WHITE));

        //Adicionar as labels a table
        /*table.add(playerLabel).expandX().padTop(40);
        table.add(worldLabel).expandX().padTop(40);*/
        table.add(scoreLabel).expandX().padTop(40);
        //table.row(); Espécie de newline e as próximas labels são desenhadas a baixo das anteriores

        stage.addActor(table); //Adicionar a table à "stage"


    }
}
