package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author //TODO your name here
 */

@Repository
public class AlbumDaoImpl implements AlbumDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createAlbum(Album album) {
        em.persist(album);
    }

    @Override
    public List<Album> getAllAlbums() {
        // TODO implement
        return new ArrayList<>();
    }

    @Override
    public Album getAlbumById(Long albumId) {
        // TODO implement
        return null;
    }

    @Override
    public List<Album> getAlbumByTitle(String title) {
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
