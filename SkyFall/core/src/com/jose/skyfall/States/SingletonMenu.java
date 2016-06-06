package com.jose.skyfall.States;

import com.jose.skyfall.Logic.SkyFall;

/**
 * Created by Bruno on 06/06/2016.
 */


public class SingletonMenu {

    public enum Screens{MENU,GAME,HIGHSCORE,CHOOSE_WORLD_SCREEN}

    private static SkyFall game;
    private static MenuScreen menu;
    private static PlayScreen playMode;
    private static ChooseWorldScreen chooseWorldScreen;
    private static HighScoreScreen highScoreScreen;

    private static SingletonMenu ourInstance = new SingletonMenu();

    public static SingletonMenu getInstance() {
        return ourInstance;
    }

    private SingletonMenu() {
        menu = new MenuScreen(game);
        playMode=new PlayScreen(game);
        chooseWorldScreen = new ChooseWorldScreen(game);
        highScoreScreen = new HighScoreScreen(game);
    }

    public void setGame(SkyFall g) {
        game = g;
    }

    public void changeState(Screens screenType) {

        switch (screenType) {
            case MENU:
                game.setScreen(menu);
                break;

            case GAME:
                game.setScreen(playMode);
                break;

            case CHOOSE_WORLD_SCREEN:
                game.setScreen(chooseWorldScreen);
                break;

            case HIGHSCORE:
                game.setScreen(highScoreScreen);
                break;
        }
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        menu.dispose();
        playMode.dispose();
        chooseWorldScreen.dispose();
        highScoreScreen.dispose();
    }
}
