package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.data.entities.Playlist;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

public interface PlaylistService {

    /**
     * Creates Playlist
     *
     * @param playlist Playlist to be created
     */
    void createPlaylist(Playlist playlist);

    /**
     *  Return every playlist stored in DB
     *
     * @return List of all playlists, empty list if there are no playlists
     */
    List<Playlist> getAllPlaylists();

    /**
     * Returns list of playlists with specified ID
     *
     * @param playlistId Id of searched playlist
     * @return Playlist with playlist ID (unique)
     */
    Playlist getPlaylistById(Long playlistId);

    /**
     * Deletes specified playlist
     *
     * @param playlist Playlist to be deleted
     */
    void deletePlaylist(Playlist playlist);

    /**
     * Updates specified playlist information
     *
     * @param playlist Playlist to be updated
     */
    void updatePlaylist(Playlist playlist);
}
