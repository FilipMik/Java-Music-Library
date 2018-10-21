package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface SongDao {

    /**
     * Get all songs that are stored in system database
     * @return List of all songs from database, zero list of there are no songs
     */
    List<Song> getAllSongs();

    /**
     * Get a song with given id
     * @param songId Id of searched song
     * @return Song with given id
     */
    Song getSongById(int songId);

    /**
     * Add new song to the database
     * @param song Song to be added
     * @return True if song was added, false if there is already this song in databse
     */
    Boolean AddSong(Song song);

    /**
     * Remove song with given id from the database
     * @return True if song was found and deleted, false otherwise
     */
    Boolean deleteSong(int songId);

    /**
     * Update commentary of song with given id
     * @param songId If of song that will be updated
     * @param commentary New commentary of the song
     * @return True if song was found and update, false otherwise
     */
    Boolean updateSongCommentary(int songId, String commentary);
}
