package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ADD YOUR NAME WHEN IMPLEMENTING
 */

public class SongDaoImpl implements SongDao {

    private List<Song> songs = new ArrayList<>();

    @Override
    public void createSong(Song song) {
        // TODO implement
    }

    @Override
    public List<Song> getAllSongs() {
        // TODO implement
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
    public Boolean deleteSong(int songId) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean updateSongCommentary(int songId, String commentary) {
        // TODO implement
        return null;
    }
}
