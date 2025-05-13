package com.example.dotgame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BallMover extends Actor {
    private float factor = 3f;

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0f, factor * delta);
    }

    public void onTriggerEnter(GameObject other) {
        if (other.layer == 8) return;
        factor *= -1f;
    }
}
