package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.PlayListDao;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Peter Žiška
 */

@Repository
public class PlayListDaoImpl implements PlayListDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPlaylist(Playlist playlist) {
        em.persist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return em.createQuery("SELECT p FROM Playlist p order by p", Playlist.class).getResultList();
    }

    @Override
    public List<Playlist> getPlaylistsByTitle(String playlistTitle) {
        return em.createQuery("SELECT p FROM Playlist p where title = :playListTitle", Playlist.class)
                .setParameter("playListTitle", playlistTitle)
                .getResultList();
    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {
        return em.find(Playlist.class, playlistId);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        em.remove(playlist);
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        em.merge(playlist);
    }
}
