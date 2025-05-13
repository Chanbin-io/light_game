package com.example.dotgame.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.example.dotgame.MainGame;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Dot Light Game");
        config.setWindowedMode(1600, 900);
        new Lwjgl3Application(new MainGame(), config);
    }
}
