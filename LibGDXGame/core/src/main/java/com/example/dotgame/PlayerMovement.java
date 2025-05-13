package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerMovement extends Actor {
    private final Body body;
    private final Sprite sprite;
    private final float moveSpeed = 70f;
    private boolean isGrounded = false;

    public PlayerMovement(World world, Sprite sprite) {
        this.sprite = sprite;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 300);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2f, sprite.getHeight() / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();

        body.setUserData(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        handleInput();
        Vector2 pos = body.getPosition();
        sprite.setPosition(pos.x - sprite.getWidth() / 2f, pos.y - sprite.getHeight() / 2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    private void handleInput() {
        Vector2 vel = body.getLinearVelocity();
        float xVel = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xVel = -moveSpeed;
            sprite.setFlip(false, false);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xVel = moveSpeed;
            sprite.setFlip(true, false);
        }

        // ✅ 점프는 스페이스바 + 바닥 위에 있을 때만
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isGrounded) {
            body.applyLinearImpulse(new Vector2(0, 800f), body.getWorldCenter(), true);
            isGrounded = false; // 점프 후 즉시 false
        }

        // Y축은 유지하고 X축만 설정
        body.setLinearVelocity(xVel, vel.y);
    }

    public void setGrounded(boolean value) {
        this.isGrounded = value;
    }

    public Body getBody() {
        return body;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
