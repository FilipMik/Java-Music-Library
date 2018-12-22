package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * @author Filip Mik
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
        return em.createQuery("select a from Album a", Album.class).getResultList();
    }

    @Override
    public Album getAlbumById(Long albumId) {
        return em.find(Album.class, albumId);
    }

    @Override
    public List<Album> getAlbumByTitle(String title) {
        return em.createQuery("select a from Album a where title = :title", Album.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public void deleteAlbum(Album album) {
        em.remove(em.find(Album.class, album.getAlbumId()));
    }

    @Override
    public void updateAlbum(Album album) {
        em.merge(album);
    }

    @Override
    public List<Album> getAllAlbumsBetween(Date fromDate, Date toDate) {
        TypedQuery<Album> query = em.createQuery(
                        "SELECT a FROM Album a WHERE a.releaseDate BETWEEN :fromDate AND :toDate ", Album.class);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        return query.getResultList();
    }
}
