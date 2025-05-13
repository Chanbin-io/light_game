package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LightStickActor extends Actor {
    private final Sprite sprite;
    private final PlayerMovement player;
    private final BulletFactory bulletFactory;
    private final Sound shootSound;
    private final UIHud hud;

    private int maxBullets = 100;
    private int remainBullets = 100;
    private final Vector2 bulletSpawnOffset = new Vector2(0, 20);

    public LightStickActor(
        Sprite sprite,
        PlayerMovement player,
        BulletFactory bulletFactory,
        Sound shootSound,
        UIHud hud
    ) {
        this.sprite = sprite;
        this.player = player;
        this.bulletFactory = bulletFactory;
        this.shootSound = shootSound;
        this.hud = hud;

        // 스틱 크기 1/9로 축소
        sprite.setSize(sprite.getWidth() / 9f, sprite.getHeight() / 9f);

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);

        // 초기 HUD 반영
        if (hud != null) hud.updateBulletCount(remainBullets);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // 1) 플레이어 머리 위 고정
        setPosition(player.getX(), player.getY() + 32f);

        // 2) 마우스 좌표 → 스테이지 좌표
        Vector2 mouse = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Stage stage = getStage();
        if (stage == null) return;
        stage.screenToStageCoordinates(mouse);

        // 3) 회전 각도 계산 (atan2 반환값은 라디안)
        float atan = MathUtils.atan2(mouse.y - getY(), mouse.x - getX());
        float deg = atan * MathUtils.radiansToDegrees - 90f;
        setRotation(deg);

        // 4) 좌우 뒤집기: 마우스가 왼쪽에 있으면 좌향
        sprite.setFlip(mouse.x < getX(), false);

        // 5) 좌클릭 시 발사
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && remainBullets > 0) {
            if (shootSound != null) shootSound.play();

            Vector2 spawn = new Vector2(getX(), getY()).add(bulletSpawnOffset);
            bulletFactory.spawnBullet(spawn, atan * MathUtils.radiansToDegrees);

            remainBullets--;
            if (hud != null) hud.updateBulletCount(remainBullets);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setRotation(getRotation());
        sprite.draw(batch);
    }

    public void reload() {
        remainBullets = maxBullets;
        if (hud != null) hud.updateBulletCount(remainBullets);
    }

    public int getRemainBullets() {
        return remainBullets;
    }
}
