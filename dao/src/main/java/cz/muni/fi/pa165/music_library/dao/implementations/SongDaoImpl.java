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
        return em.createQuery("select s from Song s where upper(title) like upper('%"+ title + "%')", Song.class)
                .getResultList();
    }

    @Override
    public List<Song> getSongsByArtist(String artistName) {
        return em.createQuery("select s from Song s where upper(artist.name) like upper('%" + artistName + "%')", Song.class)
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
        return em.createQuery("select s from Song s where upper(album.title) like upper('%" + albumTitle + "%')", Song.class)
                .getResultList();
    }

    @Override
    public void deleteSong(Song song) {
        em.remove(em.find(Song.class, song.getSongId()));
    }

    @Override
    public void updateSong(Song song) {
        em.merge(song);
    }
}
