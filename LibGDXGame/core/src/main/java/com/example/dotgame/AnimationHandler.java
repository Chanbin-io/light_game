package com.example.dotgame;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * 기본 애니메이션 상태를 관리하는 핸들러 클래스.
 */
public class AnimationHandler {
    private final ObjectMap<String, Animation<TextureRegion>> animations = new ObjectMap<>();
    private Animation<TextureRegion> currentAnimation;
    private float stateTime = 0f;
    private boolean isLooping = true;

    public void addAnimation(String key, Animation<TextureRegion> animation, boolean loop) {
        animations.put(key, animation);
        if (currentAnimation == null) {
            setTrigger(key);
        }
        this.isLooping = loop;
    }

    public void setTrigger(String key) {
        Animation<TextureRegion> anim = animations.get(key);
        if (anim != null) {
            currentAnimation = anim;
            stateTime = 0f;
        }
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void draw(Batch batch, float x, float y) {
        if (currentAnimation != null) {
            TextureRegion frame = currentAnimation.getKeyFrame(stateTime, isLooping);
            batch.draw(frame, x, y);
        }
    }

    public void setBool(String key, boolean value) {
        if (value) setTrigger(key);
    }

    public boolean isFinished() {
        return currentAnimation != null && currentAnimation.isAnimationFinished(stateTime);
    }
}
