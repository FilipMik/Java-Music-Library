package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public class AlbumDaoImpl implements AlbumDao {

    private List<Album> albums = new ArrayList<>();

    public AlbumDaoImpl() {
        // TODO get from DB
    }

    @Override
    public List<Album> getAllALbums() {
        return albums;
    }

    @Override
    public Album getAlbumById(int albumId) {
        // TODO implement
        return null;
    }

    @Override
    public Album getAlbumByTitle(int title) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean addAlbum(Album album) {
        return false;
        // TODO implement
    }

    @Override
    public void deleteAlbum(int albumId) {
        // TODO implement
    }

    @Override
    public void updateAlbumCommentary(int albumId, String commentary) {
        // TODO implement

    }

    @Override
    public void updateAlbumArt(int albumId, Image albumArt) {
        // TODO implement
    }

    @Override
    public void updateAlbumSongs(int albumId, List<Song> songs) {
        // TODO implement
    }
}
