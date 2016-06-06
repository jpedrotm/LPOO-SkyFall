package com.jose.skyfall.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private Table table;

    private Label scoreLabel;



    public Hud(SpriteBatch sb,int score){

        viewport=new FitViewport(SkyFall.V_WIDTH,SkyFall.V_HEIGHT,new OrthographicCamera());
        stage=new Stage(viewport,sb);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel.setFontScale(3);

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(scoreLabel).right().top().expandX().padTop(20).padRight(100);

        stage.addActor(table);

    }

    public void update(float delta,int score){
        score++;
        scoreLabel.setText(String.format("%06d", score));
    }
}
