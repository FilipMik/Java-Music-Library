package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.awt.*;
import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface AlbumDao {

    /**
     * Return all albums in system
     * @return List of albums, empty list if there was no album
     */
    List<Album> getAllALbums();

    /**
     * Get album by id
     * @param albumId Id of searched album
     * @return Album with albumId id
     */
    Album getAlbumById(int albumId);

    /**
     * Get album by title
     * @param title Title of searched album
     * @return Album with given title
     */
    Album getAlbumByTitle(int title);

    /**
     * Get all songs contained in given album
     * @param albumId Id of searched album
     * @return List of songs from searched album, empty list of there was no songs
     */
    List<Song> getAlbumSongs(int albumId);

    /**
     * Add new album to system database
     * @param album Album to be added to DB
     * @return True if album was added to DB, false if album is already in DB
     */
    Boolean addAlbum(Album album);

    /**
     * Deletes album with given albumId
     * @param albumId Id of searched album
     * @return True if album was found and deleted, false if album with albumId doesnt exist
     */
    Boolean deleteAlbum(int albumId);

    /**
     * Update album commentary
     * @param albumId Id of sreached album
     * @param commentary New album commentary
     * @return True if album was found and updated, false otherwise
     */
    Boolean updateAlbumCommentary(int albumId, String commentary);

    /**
     * Update album image
     * @param albumId Id of sreached album
     * @param albumArt New album image
     * @return True if album was found and updated, false otherwise
     */
    Boolean updateAlbumArt(int albumId, Image albumArt);
}
