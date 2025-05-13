package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    private final MainGame game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Texture startTexture;
    private Texture howToTexture;

    public MainMenuScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // 💡 타이틀 배경 로드
        backgroundTexture = new Texture("the_blackout.png");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(1600, 900);

        // 버튼 이미지 로드
        startTexture = new Texture(Gdx.files.internal("start.png"));
        howToTexture = new Texture(Gdx.files.internal("how_to_play.png"));

        ImageButton startButton = new ImageButton(new TextureRegionDrawable(startTexture));
        ImageButton howToButton = new ImageButton(new TextureRegionDrawable(howToTexture));

        float screenWidth = stage.getViewport().getWorldWidth();
        float screenHeight = stage.getViewport().getWorldHeight();

        startButton.setPosition(
            (screenWidth - startButton.getWidth()) / 2f,
            screenHeight / 2f - 100
        );

        howToButton.setPosition(
            (screenWidth - howToButton.getWidth()) / 2f,
            startButton.getY() - howToButton.getHeight() - 40
        );

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level01Screen(game));
            }
        });

        stage.addActor(startButton);
        stage.addActor(howToButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backgroundSprite.draw(batch); // 배경 먼저 그림
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        backgroundTexture.dispose();
        startTexture.dispose();
        howToTexture.dispose();
    }
}
