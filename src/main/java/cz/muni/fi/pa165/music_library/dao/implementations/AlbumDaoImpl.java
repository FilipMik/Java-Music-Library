package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * @author //TODO your name here
 */

public class AlbumDaoImpl implements AlbumDao {

    @Override
    public void createAlbum(Album album) {
        // TODO implement
    }

    @Override
    public List<Album> getAllALbums() {
        // TODO implement
        return new ArrayList<>();
    }

    @Override
    public Album getAlbumById(Long albumId) {
        // TODO implement
        return null;
    }

    @Override
    public Album getAlbumByTitle(String title) {
        // TODO implement
        return null;
    }

    @Override
    public void deleteAlbum(Album album) {
        // TODO implement
    }

    @Override
    public void updateAlbum(Album album) {
        // TODO implement
    }

}
