package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.dto.PlaylistDto;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import cz.muni.fi.pa165.music_library.service.PlaylistService;
import cz.muni.fi.pa165.music_library.service.TimeService;
import cz.muni.fi.pa165.music_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Service
@Transactional
public class PlaylistFacadeImpl implements PlaylistFacade {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<PlaylistDto> getAllPlaylists() {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        return (playlists == null) ? null : beanMappingService.mapTo(playlists, PlaylistDto.class);
    }

    @Override
    public PlaylistDto findPlaylistById(Long playlistId) {
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        return (playlist == null) ? null : beanMappingService.mapTo(playlist, PlaylistDto.class);
    }

    @Override
    public List<PlaylistDto> findPlaylistsByTitle(String title) {
        List<Playlist> playlists = playlistService.getPlaylistsByTitle(title);
        return beanMappingService.mapTo(playlists, PlaylistDto.class);
    }

    @Override
    public List<PlaylistDto> findUserPlaylists(Long userId) {
        User user = userService.findUserById(userId);
        List<Playlist> playlists = user.getPlaylists();
        return beanMappingService.mapTo(playlists, PlaylistDto.class);
    }

    @Override
    public void createPlaylist(PlaylistDto playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist shouldn't be null");
        }
        Playlist playlistCreate = beanMappingService.mapTo(playlist, Playlist.class);
        playlistCreate.setDateCreated(timeService.getCurrentDate());
        playlistService.createPlaylist(playlistCreate);
    }

    @Override
    public void updatePlaylist(PlaylistDto playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Updated playlist shouldn't be null");
        }
        Playlist playlistUpdate = beanMappingService.mapTo(playlist, Playlist.class);
        playlistService.updatePlaylist(playlistUpdate);
    }

    @Override
    public void addSongs(Long playlistId, List<Long> songIds) {
        playlistService.addSongs(playlistId, songIds);
    }

    @Override
    public void addSong(Long playlistId, Long songId) {
        playlistService.addSong(playlistId, songId);
    }

    @Override
    public void removeSong(Long playlistId, Long songId) {
        playlistService.removeSong(playlistId, songId);
    }

    @Override
    public void deletePlaylist(PlaylistDto playlist) {
        playlistService.deletePlaylist(beanMappingService.mapTo(playlist, Playlist.class));
    }
}
