package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Ficko
 */

@Repository
public class ArtistDaoImpl implements ArtistDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createArtist(Artist artist) {
        em.persist(artist);
    }

    @Override
    public List<Artist> getAllArtists() {
        return em.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return em.find(Artist.class, artistId);
    }

    @Override
    public List<Artist> getArtistsByName(String artistName) {
        return em
                .createQuery("SELECT a from Artist a WHERE name = :name", Artist.class)
                .setParameter("name", artistName)
                .getResultList();
    }

    @Override
    public void deleteArtist(Artist artist) {
        em.remove(em.find(Artist.class, artist.getArtistId()));
    }

    @Override
    public void updateArtistInfo(Artist artist) {
        em.merge(artist);
    }

}
