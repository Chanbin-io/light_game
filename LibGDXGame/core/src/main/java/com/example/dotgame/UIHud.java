package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * 화면 상단 UI (탄약 수, 씬 이름 등)를 표시하는 HUD 클래스
 */
public class UIHud {
    private final Stage stage;
    private final Label bulletLabel;
    private final Label sceneLabel;

    public UIHud(Stage stage, String sceneName) {
        this.stage = stage;

        BitmapFont font = new BitmapFont(); // 기본 폰트
        LabelStyle style = new LabelStyle(font, Color.WHITE);

        bulletLabel = new Label("Bullets: 0", style);
        sceneLabel = new Label("Scene: " + sceneName, style);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);

        table.add(bulletLabel).pad(10).left().row();
        table.add(sceneLabel).pad(10).left();

        stage.addActor(table);
    }

    public void updateBulletCount(int count) {
        bulletLabel.setText("Bullets: " + count);
    }
}
