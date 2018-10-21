package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface PlayListDao {

    /**
     * Get all playlists stored in system database
     * @return List of all playlists, empty list if there are no playlists
     */
    List<Playlist> getAllPlaylists();

    /**
     * Get playlist with given id
     * @param playlistId Id of searched playlist
     * @return Found playlist
     */
    Playlist getPlaylistById(int playlistId);

    /**
     * Add new playlists to the system databse
     * @param playlist New playlist to be added
     * @return True if playlist was added, false if there is already this playlist if database
     */
    Boolean addPlaylist(Playlist playlist);

    /**
     * Remove playlist from database
     * @param playlistId Id of playlist that will be deleted
     * @return True if playlist was found and deleted, false otherwise
     */
    Boolean deletePlaylist(int playlistId);

    /**
     * Update title of playlist with given id
     * @param playlistId Id of playlist that will be updated
     * @param title New playlist title
     * @return True if playlist was found and updated, false otherwise
     */
    Boolean updatePlaylistTitle(int playlistId, String title);

    /**
     * Add new song to playlist with given id
     * @param playlistId Id of playlist in which will be new song added
     * @param song Song to be added
     * @return True if playlist was fond and new song was given, false otherwise
     */
    Boolean addSong(int playlistId, Song song);

    /**
     * Remove song from playlist with given id
     * @param playlistId Id of playlist from which will be song removed
     * @param song Song to be removed
     * @return True if playlist was found and song was found and then removed, false otherwise
     */
    Boolean removeSong(int playlistId, Song song);

    /**
     * Clear all songs from playlist with given id
     * @param playlistId Id of playlist which will have all songs cleared
     * @return True if playlist was found and song were cleared, false otherwise
     */
    Boolean clearSongs(int playlistId);
}
