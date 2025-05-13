package com.example.dotgame;

import com.badlogic.gdx.physics.box2d.*;

public class BulletContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userA = fixtureA.getBody().getUserData();
        Object userB = fixtureB.getBody().getUserData();

        if (userA instanceof BulletActor && !(userB instanceof BulletActor)) {
            ((BulletActor) userA).markForDestroy();
        } else if (userB instanceof BulletActor && !(userA instanceof BulletActor)) {
            ((BulletActor) userB).markForDestroy();
        }
    }

    @Override
    public void endContact(Contact contact) {
        // not used
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // not used
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // not used
    }
}
