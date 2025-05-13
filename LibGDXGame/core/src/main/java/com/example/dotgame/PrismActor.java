package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PrismActor extends GameObject {
    private final Sprite sprite;
    private final BulletFactory bulletFactory;
    private final Sound prismSound;

    public PrismActor(Sprite sprite, BulletFactory factory, Sound sound) {
        this.sprite = sprite;
        this.bulletFactory = factory;
        this.prismSound = sound;

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
    }

    public void trigger(Bullet bullet) {
        if (bullet == null) return;

        prismSound.play();
        float baseAngle = bullet.getRotation();

        for (int i = -1; i <= 1; i++) {
            float spawnAngle = baseAngle + i * 15f;
            bulletFactory.spawnBullet(new Vector2(getX(), getY()), spawnAngle);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setRotation(getRotation());
        sprite.draw(batch);
    }
}
