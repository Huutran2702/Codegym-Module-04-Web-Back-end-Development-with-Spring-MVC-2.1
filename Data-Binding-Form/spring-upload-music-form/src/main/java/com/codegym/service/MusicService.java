package com.codegym.service;

import com.codegym.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicService implements IMusicService{
    private List<Music> musicList = new ArrayList<>();

    @Override
    public List<Music> findAll() {
        return musicList;
    }

    @Override
    public void save(Music music) {
        musicList.add(music);
    }

    @Override
    public Music findById(int id) {
        return musicList.get(id);
    }

    @Override
    public void update(int id, Music music) {
        for (Music m : musicList) {
            if (m.getId() == id) {
                m = music;
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < musicList.size(); i++) {
            if (musicList.get(i).getId() == id) {
                musicList.remove(i);
                break;
            }
        }
    }
}
