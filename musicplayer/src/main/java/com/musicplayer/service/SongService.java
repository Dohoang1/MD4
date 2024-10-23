package com.musicplayer.service;

import com.musicplayer.model.Song;
import java.util.List;

public interface SongService {
    void addSong(Song song);
    Song getSongById(int id);
    List<Song> getAllSongs();
    void updateSong(Song song);
    void deleteSong(int id);
}