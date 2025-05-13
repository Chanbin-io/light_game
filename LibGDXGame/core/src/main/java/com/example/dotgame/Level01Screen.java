package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Level01Screen implements Screen {

    private final MainGame game;
    private Stage stage;
    private SpriteBatch batch;
    private World world;

    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    private Texture floorTexture;
    private Sprite floorSprite;

    private Texture playerTexture;
    private Sprite playerSprite;

    private PlayerMovement player;

    public Level01Screen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -100), true); // 중력
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);

        // 🖼 배경: map.png
        backgroundTexture = new Texture("map.png");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(1600, 900);

        // ▓ 바닥: white_pixel.png
        floorTexture = new Texture("white_pixel.png");
        floorSprite = new Sprite(floorTexture);
        floorSprite.setSize(1600, 20);
        floorSprite.setPosition(0, 250);

        // 바닥 바디 설정
        BodyDef floorDef = new BodyDef();
        floorDef.type = BodyDef.BodyType.StaticBody;
        floorDef.position.set(800, 260);
        Body floorBody = world.createBody(floorDef);

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(800, 10); // 절반 단위

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = floorShape;
        fixtureDef.friction = 1f;
        floorBody.createFixture(fixtureDef);
        floorShape.dispose();

        // 👤 플레이어 생성
        playerTexture = new Texture("avata.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(64, 64);
        playerSprite.setOriginCenter();

        player = new PlayerMovement(world, playerSprite);
        stage.addActor(player);

        // ✅ ContactListener: 점프 가능 여부 설정
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Object a = contact.getFixtureA().getBody().getUserData();
                Object b = contact.getFixtureB().getBody().getUserData();

                if (a instanceof PlayerMovement) ((PlayerMovement) a).setGrounded(true);
                if (b instanceof PlayerMovement) ((PlayerMovement) b).setGrounded(true);
            }

            @Override
            public void endContact(Contact contact) {
                Object a = contact.getFixtureA().getBody().getUserData();
                Object b = contact.getFixtureB().getBody().getUserData();

                if (a instanceof PlayerMovement) ((PlayerMovement) a).setGrounded(false);
                if (b instanceof PlayerMovement) ((PlayerMovement) b).setGrounded(false);
            }

            @Override public void preSolve(Contact contact, Manifold oldManifold) {}
            @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
        });
    }

    @Override
    public void render(float delta) {
        world.step(1 / 60f, 6, 2);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backgroundSprite.draw(batch);
        floorSprite.draw(batch);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        world.dispose();
        backgroundTexture.dispose();
        floorTexture.dispose();
        playerTexture.dispose();
    }
}
