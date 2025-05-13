package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level00Screen implements Screen {
    private final MainGame game;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private SpriteBatch batch;

    private Texture playerTexture;
    private Sprite playerSprite;
    private Body playerBody;

    public Level00Screen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        viewport = new FitViewport(1600, 900, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        // avata.png 사용
        playerTexture = new Texture("avata.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(64, 64);
        playerSprite.setOriginCenter();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(800, 450);
        playerBody = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(32);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        playerBody.createFixture(fixtureDef);
        shape.dispose();

        playerBody.setUserData("player");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 6, 2);

        Vector2 pos = playerBody.getPosition();
        playerSprite.setPosition(pos.x - playerSprite.getWidth() / 2f, pos.y - playerSprite.getHeight() / 2f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        playerSprite.draw(batch);
        batch.end();

        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        batch.dispose();
        playerTexture.dispose();
        stage.dispose();
    }
}
