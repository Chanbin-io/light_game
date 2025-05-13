package com.example.dotgame;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * 공통 게임 오브젝트용 베이스 클래스
 */
public class GameObject extends Actor {
    public int layer = 0;

    public void setActive(boolean active) {
        this.setVisible(active);
        this.setTouchable(active ? Touchable.enabled : Touchable.disabled);
    }
}
