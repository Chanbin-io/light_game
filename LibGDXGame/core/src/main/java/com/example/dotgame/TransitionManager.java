package com.example.dotgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * 화면 전환(페이드 인/아웃) 연출을 처리하는 매니저
 */
public class TransitionManager {

    private static Game game;
    private static final float duration = 0.5f;

    private static boolean inTransition = false;
    private static boolean fadeOut = true;
    private static float time = 0f;

    private static Screen nextScreen;
    private static final Color overlayColor = new Color(0, 0, 0, 0);
    private static ShapeRenderer shapeRenderer;

    public static void initialize(Game g) {
        game = g;
        shapeRenderer = new ShapeRenderer();
    }

    public static void transitionTo(Screen screen) {
        if (inTransition) return;

        inTransition = true;
        nextScreen = screen;
        time = 0f;
        fadeOut = true;
    }

    public static void update(float delta) {
        if (!inTransition) return;

        time += delta;
        float progress = Math.min(time / duration, 1f);
        overlayColor.a = fadeOut ? progress : (1f - progress);

        if (progress >= 1f) {
            if (fadeOut) {
                game.setScreen(nextScreen);
                fadeOut = false;
                time = 0f;
            } else {
                inTransition = false;
            }
        }
    }

    public static void renderOverlay() {
        if (!inTransition) return;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(overlayColor);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
    }

    public static void dispose() {
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
            shapeRenderer = null;
        }
    }

    public static boolean isInTransition() {
        return inTransition;
    }
}
