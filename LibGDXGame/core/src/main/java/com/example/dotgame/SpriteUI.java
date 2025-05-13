package com.example.dotgame;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * 단순 UI Sprite 보관용 클래스.
 */
public class SpriteUI {
    private Sprite sprite;

    public SpriteUI() {}

    public SpriteUI(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSprite(Sprite newSprite) {
        this.sprite = newSprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isSet() {
        return sprite != null;
    }
}
