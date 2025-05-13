package com.example.dotgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BulletFactory {
    private final Stage stage;
    private final World world;
    private final Texture bulletTexture;

    public BulletFactory(Stage stage, World world) {
        this.stage = stage;
        this.world = world;
        this.bulletTexture = new Texture("light.png");
    }

    public void spawnBullet(Vector2 position, float rotationDeg) {
        Sprite bulletSprite = new Sprite(bulletTexture);
        bulletSprite.setSize(
            bulletSprite.getWidth() / 9f,
            bulletSprite.getHeight() / 9f
        );
        BulletActor bullet = new BulletActor(
            bulletSprite, position, rotationDeg, 600f, world
        );
        stage.addActor(bullet);
    }


    public void dispose() {
        bulletTexture.dispose();
    }
}
