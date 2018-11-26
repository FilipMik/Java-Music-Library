package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.data.entities.Album;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

public interface AlbumService {

    /**
     * Returns list of albums with specified ID
     *
     * @param albumId Id of Album
     * @return Album with album ID (unique)
     */
    Album getAlbumById(Long albumId);

    /**
     * Returns list of albums with specified title
     *
     * @param title Title of albums to be found
     * @return List of Albums with specified title
     */
    List<Album> getAlbumByTitle(String title);

    /**
     * Return every album stored in DB
     *
     * @return List of Albums in DB
     */
    List<Album> getAllAlbums();

    /**
     * Deletes specified album
     *
     * @param album Album to be deleted
     */
    void deleteAlbum(Album album);

    /**
     * Updates specified album
     *
     * @param album Album to be updated
     */
    void updateAlbum(Album album);

    /**
     * Creates album
     *
     * @param album Album to be created
     */
    void createAlbum(Album album);

    /**
     * Returns albums, which were created last week
     * @return list of albums
     */
    List<Album> getLastWeekAlbums();
}
