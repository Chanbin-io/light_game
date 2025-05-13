package com.example.dotgame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.example.dotgame.Constants.*;

public class BulletActor extends Actor {
    private final Sprite sprite;
    private Body body;
    private final World world;
    private boolean destroyed = false;

    public BulletActor(Sprite sprite, Vector2 position, float rotationDeg, float speed, World world) {
        this.sprite = sprite;
        this.world = world;

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setPosition(position.x, position.y);

        createBody(position, rotationDeg, speed);
    }

    private void createBody(Vector2 position, float rotationDeg, float speed) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(position);
        def.bullet = true;

        body = world.createBody(def);

        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() / 2f);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fdef.restitution = 0.1f;
        fdef.filter.categoryBits = CATEGORY_BULLET;
        fdef.filter.maskBits = CATEGORY_WALL | CATEGORY_MIRROR | CATEGORY_PRISM;

        body.createFixture(fdef);
        shape.dispose();

        float rad = rotationDeg * MathUtils.degreesToRadians;
        Vector2 vel = new Vector2(MathUtils.cos(rad), MathUtils.sin(rad)).scl(speed);
        body.setLinearVelocity(vel);
        body.setUserData(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (destroyed || body == null) return;

        Vector2 pos = body.getPosition();
        setPosition(pos.x, pos.y);
        sprite.setPosition(pos.x, pos.y);

        if (pos.x < -50f || pos.x > 1650f || pos.y < -50f || pos.y > 950f) {
            destroy();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!destroyed) {
            sprite.draw(batch);
        }
    }

    public void destroy() {
        if (!destroyed) {
            destroyed = true;
            if (body != null) {
                world.destroyBody(body);
                body = null;
            }
            remove();
        }
    }

    public void markForDestroy() {
        destroy();
    }
}
