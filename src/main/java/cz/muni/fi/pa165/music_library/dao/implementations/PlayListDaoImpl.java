package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.PlayListDao;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public class PlayListDaoImpl implements PlayListDao {

    private List<Playlist> playlists = new ArrayList<>();

    public PlayListDaoImpl() {
        // TODO implement
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlists;
    }

    @Override
    public Playlist getPlaylistById(int playlistId) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean addPlaylist(Playlist playlist) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean deletePlaylist(int playlistId) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean updatePlaylistTitle(int playlistId, String title) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean addSong(int playlistId, Song song) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean removeSong(int playlistId, Song song) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean clearSongs(int playlistId) {
        // TODO implement
        return null;
    }
}
