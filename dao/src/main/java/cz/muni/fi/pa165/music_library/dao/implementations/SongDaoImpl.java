package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Genre;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public List<Song> getAllSongsByRating(Genre genre) {
        List<Song> list;
        if (genre == null) {
            list = em.createQuery("select s from Song s order by s.rating desc", Song.class)
                    .getResultList();
        } else {
            list = em.createQuery("select s from Song s where genre = :genre order by s.rating desc", Song.class)
                    .setParameter("genre", genre)
                    .getResultList();
        }
        return list;
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
    public List<Song> getSongsByArtist(String artistName) {
        return em.createQuery("select s from Song s where artist.name = :artistName", Song.class)
                .setParameter("artistName", artistName)
                .getResultList();
    }

    @Override
    public List<Song> getSongsByGenre(Genre genre) {
        return em.createQuery("select s from Song s where genre = :genre", Song.class)
                .setParameter("genre", genre)
                .getResultList();
    }

    @Override
    public List<Song> getSongsByAlbum(String albumTitle) {
        return em.createQuery("select s from Song s where album.title = :albumTitle", Song.class)
                .setParameter("albumTitle", albumTitle)
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
