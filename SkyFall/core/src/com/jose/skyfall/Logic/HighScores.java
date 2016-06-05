package com.jose.skyfall.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

/**
 * Created by Jose on 04/06/2016.
 */
public class HighScores {

    private String HIGHSCORE_FILE_NAME="world";
    private String HIGHSCORE_FILE_EXTENSION=".dat";

    private int highscore;
    private int score;
    private int world;

    public HighScores(int world){

        this.score=0;
        this.world=world;
        loadHighscore();

    }

    public int getScore(){
        return score;
    }

    public void update(int val){

        score+=val;
    }

    public void loadHighscore() {
        Gdx.app.log("entrou load","entrou load");
        FileHandle highscoreF = Gdx.files.local(HIGHSCORE_FILE_NAME + world + HIGHSCORE_FILE_EXTENSION);

        if(highscoreF.exists()) {
            String highscoreString = highscoreF.readString();
            highscore = Integer.parseInt(highscoreString);
            Gdx.app.log("existe","existe: "+highscore);
        }
        else{
            highscore = 0;
            Gdx.app.log("highscore load: ","highscore load: "+highscore);
        }
    }

    public void saveScore() {
        Gdx.app.log("score","score: "+score);
        Gdx.app.log("highscore","highscore: "+highscore);
        if(score > highscore) {
            FileHandle highscoreF = Gdx.files.local(HIGHSCORE_FILE_NAME + world + HIGHSCORE_FILE_EXTENSION);

            try {
                if(!highscoreF.exists()) {
                    highscoreF.file().createNewFile();
                }

                highscoreF.writeString(new Integer(score).toString(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
