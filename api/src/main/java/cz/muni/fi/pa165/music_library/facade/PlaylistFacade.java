package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.dto.PlaylistDto;

import java.util.List;

/**
 * @author Jan Ficko
 */
public interface PlaylistFacade {

    List<PlaylistDto> getAllPlaylists();

    PlaylistDto findPlaylistById(Long playlistId);

    List<PlaylistDto> findPlaylistsByTitle(String title);

    List<PlaylistDto> findUsersPlaylists(Long userId);

    void createPlaylist(PlaylistDto playlist);

    void updatePlaylist(PlaylistDto playlist);

    void addSongs(Long playlistId, List<Long> songIds);

    void addSong(Long playlistId, Long songId);

    void removeSong(Long playlistId, Long songId);

    void deletePlaylist(Long playlistId);

}
