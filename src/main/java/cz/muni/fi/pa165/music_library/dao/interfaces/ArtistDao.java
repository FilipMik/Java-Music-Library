package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Artist;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface ArtistDao {

    /**
     * Get all artist stored in system database
     * @return List of all artists
     */
    List<Artist> getAllArtists();

    /**
     * Get artist with given id
     * @param artistId Id of searched artist
     * @return Found artist with given id
     */
    Artist getArtistById(int artistId);

    /**
     * Get artist with given name
     * @param artistName Name of searched artist
     * @return Found artist with given name
     */
    Artist getArtistByName(int artistName);

    /**
     * Add new artists to database
     * @param artist New artists object to be added to database
     * @return True if artist was added, false if the artist already exists in database
     */
    Boolean AddArtist(Artist artist);

    /**
     * Remove artist with given id from database
     * @param artistId Id of artists that will be removed from database
     * @return True if artists was found and deleted, false if artist with given id doesnt exist
     */
    Boolean deleteArtist(int artistId);

    /**
     * Update artist info
     * @param artistId Id of artists that will be updated
     * @param info New artists info
     * @return True if artists was found and update, false if artist with given id doesnt exist
     */
    Boolean updateArtistInfo(int artistId, String info);

    /**
     * Update artist info
     * @param artistId Id of artists that will be updated
     * @param songsCreated New artists count of songsCreated
     * @return True if artists was found and update, false if artist with given id doesnt exist
     */
    Boolean updateArtistSongsCreated(int artistId, int songsCreated);
}
