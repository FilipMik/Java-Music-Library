package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.PlayListDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
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

    @Autowired
    private SongDao songDao;

    @Override
    public void createPlaylist(Playlist playlist) {
        playListDao.createPlaylist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playListDao.getAllPlaylists();
    }

    @Override
    public List<Playlist> getPlaylistsByTitle(String title) {
        return playListDao.getPlaylistsByTitle(title);
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

    @Override
    public void addSongs(Long playlistId, List<Long> songIds) {
        Playlist playlist = playListDao.getPlaylistById(playlistId);
        for (Long songId : songIds) {
            playlist.addSong(songDao.getSongById(songId));
        }
        playListDao.updatePlaylist(playlist);
    }

    @Override
    public void addSong(Long playlistId, Long songId) {
        Playlist playlist = playListDao.getPlaylistById(playlistId);
        playlist.addSong(songDao.getSongById(songId));
        playListDao.updatePlaylist(playlist);
    }

    @Override
    public void removeSong(Long playlistId, Long songId) {
        Playlist playlist = playListDao.getPlaylistById(playlistId);
        playlist.removeSong(songDao.getSongById(songId));
        playListDao.updatePlaylist(playlist);
    }
}
