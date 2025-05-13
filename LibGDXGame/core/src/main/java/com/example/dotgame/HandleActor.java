package com.example.dotgame;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HandleActor extends GameObject {
    private final Sprite sprite;
    private final Sound rotateSound;
    private int rotationState = 0;

    public HandleActor(Sprite sprite, Sound rotateSound) {
        this.sprite = sprite;
        this.rotateSound = rotateSound;

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
    }

    public void rotate(int direction) {
        rotationState += direction;
        float newRotation = getRotation() + 90f * direction;
        setRotation(newRotation);
        rotateSound.play();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setRotation(getRotation());
        sprite.draw(batch);
    }

    public int getRotationState() {
        return rotationState;
    }
}
