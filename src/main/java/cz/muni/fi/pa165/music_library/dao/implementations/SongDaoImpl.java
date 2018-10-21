package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public class SongDaoImpl implements SongDao {

    private List<Song> songs = new ArrayList<>();

    public SongDaoImpl() {
        // TODO implement
    }

    @Override
    public List<Song> getAllSongs() {
        return songs;
    }

    @Override
    public Song getSongById(int songId) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean AddSong(Song song) {
        // TODO implement
        return null;
    }

    @Override
    public void deleteSong() {
        // TODO implement
    }

    @Override
    public void updateSongCommentary(int songId, String commentary) {
        // TODO implement
    }
}
