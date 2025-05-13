package com.example.dotgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimationLoader {
    public static AnimationHandler load() {
        AnimationHandler handler = new AnimationHandler();

        // avata.png 하나로 양방향 애니메이션 구성
        Texture avataTex = new Texture("avata.png");

        TextureRegion left = new TextureRegion(avataTex);
        TextureRegion right = new TextureRegion(avataTex);
        right.flip(true, false); // 좌우 반전

        Animation<TextureRegion> idle = new Animation<>(0.1f, right);
        Animation<TextureRegion> walkRight = new Animation<>(0.1f, right);
        Animation<TextureRegion> walkLeft = new Animation<>(0.1f, left);

        handler.addAnimation("idle", idle, true);
        handler.addAnimation("walkRight", walkRight, true);
        handler.addAnimation("walkLeft", walkLeft, true);

        return handler;
    }
}
