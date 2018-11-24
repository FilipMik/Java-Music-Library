package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface SongDao {

    /**
     * Crates new song and add to database
     *
     * @param song Song to be added to database
     */
    void createSong(Song song);

    /**
     * Get all songs that are stored in system database
     *
     * @return List of all songs from database, zero list of there are no songs
     */
    List<Song> getAllSongs();

    /**
     * Get all songs ordered by best rating
     * @return List of all songs from database ordered by best rating
     */
    List<Song> getAllSongsByRating();

    /**
     * Get given song
     *
     * @param songId Id Searched song
     * @return Found song
     */
    Song getSongById(Long songId);

    /**
     * Get all songs with given name/title
     * @param title Title of songs to be found
     * @return List of found songs
     */
    List<Song> getSongsByTitle(String title);

    /**
     * Remove given song from persistence(database)
     *
     * @param song Song that will be deleted
     */
    void deleteSong(Song song);

    /**
     * Update given song
     *
     * @param song Song that will be updated
     */
    void updateSong(Song song);
}
