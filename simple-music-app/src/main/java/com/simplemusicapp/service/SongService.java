package com.simplemusicapp.service;

import com.simplemusicapp.model.Song;
import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findById(int id);
    void save(Song song);
    void update(Song song);
    void delete(int id);
}