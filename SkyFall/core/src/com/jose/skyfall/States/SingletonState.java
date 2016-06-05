package com.jose.skyfall.States;

import com.jose.skyfall.Logic.SkyFall;

public class SingletonState {

    public enum ScreenState{MENU,GAME,HIGHSCORE,CHOOSE_WORLD_SCREEN}

    private static SkyFall game;
    private static MenuScreen menu;
    private static PlayScreen playMode;
    private static ChooseWorldScreen chooseWorldScreen;
    private static HighScoreScreen highScoreScreen;

    private static SingletonState ourInstance = new SingletonState();

    public static SingletonState getInstance() {
        return ourInstance;
    }

    private SingletonState() {
        menu = new MenuScreen(game);
        playMode=new PlayScreen(game);
        chooseWorldScreen = new ChooseWorldScreen(game);
        highScoreScreen = new HighScoreScreen(game);
    }

    public void setGame(SkyFall g) {
        game = g;
    }

    public void changeState(ScreenState screenType) {

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
