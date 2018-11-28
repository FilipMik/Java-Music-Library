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
     * Return every playlist stored in DB
     *
     * @return List of all playlists, empty list if there are no playlists
     */
    List<Playlist> getAllPlaylists();

    /**
     * Return playlist with given name stored in DB
     *
     * @param title Playlist title
     * @return List of all playlists with given name, empty list if there are no playlists
     */
    List<Playlist> getPlaylistsByTitle(String title);

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

    /**
     * Add song to playlist
     *
     * @param playlistId Id of playlist
     * @param songId     Id of song
     */
    void addSong(Long playlistId, Long songId);

    /**
     * Delete song from playlist
     *
     * @param playlistId Id of playlist
     * @param songId     Id of song
     */
    void removeSong(Long playlistId, Long songId);
}
