package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.PlayListDao;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */
@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlayListDao playListDao;

    @Override
    public void createPlaylist(Playlist playlist) {
        playListDao.createPlaylist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playListDao.getAllPlaylists();
    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {
        return playListDao.getPlaylistById(playlistId);
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        playListDao.deletePlaylist(playlist);
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        playListDao.updatePlaylist(playlist);
    }
}
