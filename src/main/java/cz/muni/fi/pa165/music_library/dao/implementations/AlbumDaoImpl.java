package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public class AlbumDaoImpl implements AlbumDao {

    private List<Album> albums;

    public AlbumDaoImpl() {
        // TODO get from DB
    }

    @Override
    public List<Album> getAllALbums() {
        return albums;
    }

    @Override
    public Album getAlbum(int albumId) {
        // TODO implement
        return null;
    }

    @Override
    public Album getAlbumByTitle(int title) {
        // TODO implement
        return null;
    }

    @Override
    public void addAlbum(Album album) {
        // TODO implement
    }

    @Override
    public void deleteAlbum(int albumId) {
        // TODO implement
    }

    @Override
    public void updateAlbum(int albumId) {
        // TODO implement
    }
}
