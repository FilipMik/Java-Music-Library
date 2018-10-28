package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Album;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface AlbumDao {

    /**
     * Store album to persistence
     *
     * @param album Album to be stored in persistence
     */
    void createAlbum(Album album);

    /**
     * Return all albums in system
     *
     * @return List of albums, empty list if there was no album
     */
    List<Album> getAllAlbums();

    /**
     * Get album by id
     *
     * @param albumId Id of searched album
     * @return Album with albumId id
     */
    Album getAlbumById(Long albumId);

    /**
     * Get albums wtih given title
     *
     * @param title Title of searched album
     * @return List of albums with given title
     */
    List<Album> getAlbumByTitle(String title);

    /**
     * Deletes given album
     *
     * @param album Album to be deleted
     */
    void deleteAlbum(Album album);

    /**
     * Update album
     *
     * @param album Album tu be updated
     */
    void updateAlbum(Album album);

}
