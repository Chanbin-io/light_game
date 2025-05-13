package com.example.dotgame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.example.dotgame.Constants.*;

/**
 * 고정된 벽 오브젝트. Box2D 충돌체 포함.
 */
public class WallActor extends Actor {
    private final Sprite sprite;
    private final Body body;

    public WallActor(Sprite sprite, Vector2 position, World world) {
        this.sprite = sprite;

        setPosition(position.x, position.y);
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(position);

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2f, getHeight() / 2f);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.filter.categoryBits = CATEGORY_WALL;
        fdef.filter.maskBits = CATEGORY_BULLET;

        body.createFixture(fdef);
        body.setUserData(this);

        shape.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setRotation(getRotation());
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
