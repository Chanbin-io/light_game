package com.example.dotgame;

import com.badlogic.gdx.Game;

public class MainGame extends Game {

    @Override
    public void create() {
        TransitionManager.initialize(this);
        setScreen(new MainMenuScreen(this));
    }
}
