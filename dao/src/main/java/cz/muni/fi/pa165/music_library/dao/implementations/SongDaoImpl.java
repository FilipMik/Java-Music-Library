package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Filip Mik
 */

@Repository
public class SongDaoImpl implements SongDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createSong(Song song) {
        em.persist(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return em.createQuery("select s from Song s", Song.class).getResultList();
    }

    @Override
    public List<Song> getAllSongsByRating() {
        return em.createQuery("select s from Song s order by s.rating asc", Song.class).getResultList();
    }

    @Override
    public Song getSongById(Long songId) {
        return em.find(Song.class, songId);
    }

    @Override
    public List<Song> getSongsByTitle(String title) {
        return em.createQuery("select s from Song s where title = :title", Song.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public void deleteSong(Song song) {
        em.remove(song);
    }

    @Override
    public void updateSong(Song song) {
        em.merge(song);
    }
}
