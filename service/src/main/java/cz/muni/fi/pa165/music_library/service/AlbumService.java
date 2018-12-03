package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.data.entities.Song;


import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

public interface AlbumService {

    /**
     * Return every album stored in DB
     *
     * @return List of Albums in DB
     */
    List<Album> getAllAlbums();

    /**
     * Returns list of albums with specified ID
     *
     * @param albumId Id of Album
     * @return Album with album ID (unique)
     */
    Album getAlbumById(Long albumId);

    /**
     * Get artists of given album
     * @param albumId Id of searched album
     * @return Artists of album
     */
    public List<Artist> getAlbumArtists(Long albumId);

    /**
     * Returns list of albums with specified title
     *
     * @param title Title of albums to be found
     * @return List of Albums with specified title
     */
    List<Album> getAlbumsByTitle(String title);

    /**
     * Get all albums from given artist
     * @param artistId Arists id
     * @return All albums by given artist
     */
    List<Album> getAlbumsByArtist(Long artistId);

    /**
     * Get all albums from given artist name
     * @param artistName Arists name
     * @return All albums by given artist name
     */
    List<Album> getAlbumsByArtistName(String artistName);

    /**
     * Returns albums, which were created last week
     * @return list of albums
     */
    List<Album> getLastWeekAlbums();

    /**
     * Creates album
     *
     * @param album Album to be created
     */
    void createAlbum(Album album);

    /**
     * Updates specified album
     *
     * @param album Album to be updated
     */
    void updateAlbum(Album album);

    /**
     * Add multiple songs to album
     * @param albumId Id of album
     * @param songIds List of songs to be added
     */
    void addSongs(Long albumId, List<Long> songIds);

    /**
     * Add song to album
     * @param albumId Album id
     * @param songId Id of song to be added
     */
    void addSong(Long albumId, Long songId);

    /**
     * Remove song from album
     * @param albumId ALbum id
     * @param songId Id of song to be deleted
     */
    void removeSong(Long albumId, Long songId);

    /**
     * Deletes specified album
     *
     * @param album Album to be deleted
     */
    void deleteAlbum(Album album);
}
