package com.musicplayer.service;

import com.musicplayer.model.Song;
import com.musicplayer.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public void addSong(Song song) {
        songRepository.saveSong(song);
    }

    @Override
    public Song getSongById(int id) {
        return songRepository.getSongById(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }

    @Override
    public void updateSong(Song song) {
        songRepository.updateSong(song);
    }

    @Override
    public void deleteSong(int id) {
        Song song = songRepository.getSongById(id);
        if (song != null) {
            songRepository.deleteSong(song);
        }
    }
}