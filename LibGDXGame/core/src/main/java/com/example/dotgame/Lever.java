package com.example.dotgame;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Lever extends GameObject {
    public boolean leverDown = false;

    private Sprite sprite;
    private Sprite upSprite;
    private Sprite downSprite;

    private Sound touch;
    private Sound clear;

    public Lever(Sprite sprite, Sprite upSprite, Sprite downSprite, Sound touch, Sound clear) {
        this.sprite = sprite;
        this.upSprite = upSprite;
        this.downSprite = downSprite;
        this.touch = touch;
        this.clear = clear;

        if (this.upSprite != null) {
            this.sprite.setRegion(upSprite);
        }
    }

    public void touchBullet(float bulletTime) {
        if (!leverDown) {
            leverDown = true;

            if (downSprite != null) {
                sprite.setRegion(downSprite);
            }

            if (touch != null) {
                touch.play();
            }

            // bulletTime 매개변수는 현재 사용하지 않음 (추후 딜레이 활용 가능)
        }
    }

    public void reset() {
        leverDown = false;
        if (upSprite != null) {
            sprite.setRegion(upSprite);
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}
