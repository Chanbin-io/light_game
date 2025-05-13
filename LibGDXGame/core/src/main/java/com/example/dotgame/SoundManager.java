package com.example.dotgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;
import java.util.Map;

/**
 * 게임 내 배경음악(BGM)을 관리하는 싱글톤 클래스
 */
public class SoundManager {

    private static SoundManager instance;
    private Music currentMusic;
    private final Map<Integer, Music> bgmMap = new HashMap<>();

    private SoundManager() {
        loadBGM();
    }

    private void loadBGM() {
        bgmMap.put(0, Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_0.mp3")));
        bgmMap.put(1, Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_1.mp3")));
        // 필요시 더 추가 가능
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void playBGM(int id) {
        Gdx.app.log("SoundManager", "BGM Change to --> " + id);

        if (currentMusic != null) {
            currentMusic.stop();
        }

        Music newMusic = bgmMap.get(id);
        if (newMusic != null) {
            currentMusic = newMusic;
            currentMusic.setLooping(true);
            currentMusic.play();
        } else {
            Gdx.app.error("SoundManager", "No music found for ID: " + id);
        }
    }

    public void stopBGM() {
        if (currentMusic != null) {
            currentMusic.stop();
        }
    }

    public void dispose() {
        stopBGM();
        for (Music music : bgmMap.values()) {
            music.dispose();
        }
        bgmMap.clear();
    }
}
