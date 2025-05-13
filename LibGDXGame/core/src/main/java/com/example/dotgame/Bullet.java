package com.example.dotgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;      // ← 이 줄이 반드시 필요합니다
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

public class Bullet {
    public boolean isSpawnedByMirror = false;
    public float mirrorCheckDistance = 0.36f;

    public float bulletSpeed = 1f;
    public float scaleSpeed = 1f;
    public float bulletLightIntensitySpeed = 0f;

    public float touchScaleSpeed = 1f;
    public float touchLightIntensitySpeed = 0f;
    public Vector3 touchScale = new Vector3(0, 0, 0);

    private boolean touched = false;

    public Light bulletLight;
    public Light touchLight;
    public Vector3 position;
    public Vector3 scale;
    public float rotationZ;

    public Bullet(Vector3 startPosition, float rotationZ) {
        this.position = new Vector3(startPosition);
        this.rotationZ = rotationZ;
        this.scale = new Vector3(1, 1, 1);

        bulletLight = new Light(true);
        touchLight = new Light(false);
    }

    public void update(float delta) {
        if (!touched) {
            bulletLight.intensity -= bulletLightIntensitySpeed * delta;
            Vector3 forward = getForwardVector(rotationZ);
            position.add(forward.scl(bulletSpeed * delta));
            scale.sub(new Vector3(scaleSpeed, scaleSpeed, scaleSpeed).scl(delta));
        } else {
            touchLight.intensity -= touchLightIntensitySpeed * delta;
            scale.sub(new Vector3(touchScaleSpeed, touchScaleSpeed, touchScaleSpeed).scl(delta));
        }

        if (scale.x <= 0f) {
            destroy();
        }
    }

    public Vector3 getForwardVector(float angle) {
        float radians = (angle + 90f) * MathUtils.degreesToRadians;
        return new Vector3(
            MathUtils.cos(radians),
            MathUtils.sin(radians),
            0f
        );
    }

    public void onTriggerEnter(GameObject other) {
        int layer = other.layer;
        switch (layer) {
            case 10:
                touched = true;
                bulletLight.setActive(false);
                touchLight.setActive(true);
                scale.set(touchScale);
                if (other instanceof Lever) {
                    ((Lever) other).touchBullet(scale.x / touchScaleSpeed);
                }
                break;
            case 12:
                if (other instanceof MirrorActor) {
                    ((MirrorActor) other).reflect(this);
                }
                break;
            case 13:
                if (other instanceof HandleActor) {
                    ((HandleActor) other).rotate(1);
                }
                break;
            case 14:
                destroy();
                break;
            case 15:
                if (other instanceof PrismActor) {
                    ((PrismActor) other).trigger(this);
                }
                break;
            default:
                break;
        }
    }

    public void start() {
        if (isSpawnedByMirror) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    noMirror();
                }
            }, 0.05f);
        }
    }

    private void noMirror() {
        isSpawnedByMirror = false;
    }

    public void drawDebug(ShapeRenderer renderer) {
        Vector3 forward = getForwardVector(rotationZ);
        renderer.setColor(Color.RED);
        renderer.line(position.x, position.y,
            position.x + forward.x, position.y + forward.y);

        renderer.setColor(Color.GREEN);
        renderer.line(position.x, position.y,
            position.x - mirrorCheckDistance, position.y);
        renderer.line(position.x, position.y,
            position.x + mirrorCheckDistance, position.y);
    }

    private void destroy() {
        // 여기에 필요하다면 풀에서 제거하는 로직 추가
    }

    public float getRotation() {
        return rotationZ;
    }

    public static class Light {
        public boolean active;
        public float intensity = 1f;

        public Light(boolean active) {
            this.active = active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }
    }
}
