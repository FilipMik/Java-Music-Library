package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Playlist;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface PlayListDao {

    /**
     * Store new playlist in persistence
     *
     * @param playlist Playlist that will be added to persistence(database)
     */
    void createPlaylist(Playlist playlist);

    /**
     * Get all playlists stored in system database
     *
     * @return List of all playlists, empty list if there are no playlists
     */
    List<Playlist> getAllPlaylists();

    /**
     * Get all playlists with given title
     * @param playlistTitle Playlists name
     * @return All playlists with given name
     */
    List<Playlist> getPlaylistsByTitle(String playlistTitle);

    /**
     * Get playlist with given id
     *
     * @param playlistId Id of searched playlist
     * @return Found playlist
     */
    Playlist getPlaylistById(Long playlistId);

    /**
     * Remove playlist from database
     *
     * @param playlist Playlist that will be deleted
     */
    void deletePlaylist(Playlist playlist);

    /**
     * Update given playlist
     *
     * @param playlist Playlist that will be updated
     */
    void updatePlaylist(Playlist playlist);
}
