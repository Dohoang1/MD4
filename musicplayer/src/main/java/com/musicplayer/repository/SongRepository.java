package com.musicplayer.repository;

import com.musicplayer.model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveSong(Song song) {
        getCurrentSession().save(song);
    }

    public Song getSongById(int id) {
        return getCurrentSession().get(Song.class, id);
    }

    public List<Song> getAllSongs() {
        return getCurrentSession().createQuery("from Song", Song.class).list();
    }

    public void updateSong(Song song) {
        getCurrentSession().update(song);
    }

    public void deleteSong(Song song) {
        getCurrentSession().delete(song);
    }
}