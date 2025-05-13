package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MirrorActor extends GameObject {
    private Sprite sprite;
    private BulletFactory bulletFactory;
    private Sound reflectSound;
    private float checkStep = 5f;

    public MirrorActor(Sprite sprite, BulletFactory factory, Sound reflectSound) {
        this.sprite = sprite;
        this.bulletFactory = factory;
        this.reflectSound = reflectSound;
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
    }

    public void reflect(Bullet bullet) {
        if (bullet == null) return;

        reflectSound.play();

        float incomingAngle = bullet.getRotation();
        float mirrorAngle = getRotation();
        float reflectedAngle = 2 * mirrorAngle - incomingAngle;

        Gdx.app.log("Mirror", "Reflecting angle: " + incomingAngle + " -> " + reflectedAngle);

        Vector2 spawnPos = new Vector2(getX(), getY());
        bulletFactory.spawnBullet(spawnPos, reflectedAngle);
    }

    @Override
    public void draw(com.badlogic.gdx.graphics.g2d.Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setRotation(getRotation());
        sprite.draw(batch);
    }
}
