package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Genre;
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
     * Get all songs ordered by rating in descending order
     * @param genre Optional genre of songs what are requested
     * @return List of songs with given optional genre ordered by their rating
     */
    List<Song> getAllSongsByRating(Genre genre);

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
     * Get all songs created by given artist
     * @param artistName Artist name
     * @return All artists songs
     */
    List<Song> getSongsByArtist(String artistName);

    /**
     * Get all songs created from given genre
     * @param genre Given genre
     * @return All genre songs
     */
    List<Song> getSongsByGenre(Genre genre);

    /**
     * Get all songs in given album
     * @param albumTitle Albums name
     * @return All album songs
     */
    List<Song> getSongsByAlbum(String albumTitle);

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
