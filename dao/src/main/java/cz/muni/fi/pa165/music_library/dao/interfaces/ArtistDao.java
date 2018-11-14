package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Artist;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface ArtistDao {

    /**
     * Store artist to persistence
     *
     * @param artist Artist to be stored in persistence
     */
    void createArtist(Artist artist);

    /**
     * Get all artist stored in system database
     *
     * @return List of all artists
     */
    List<Artist> getAllArtists();

    /**
     * Get artist with given id
     *
     * @param artistId Id of searched artist
     * @return Found artist with given id
     */
    Artist getArtistById(Long artistId);

    /**
     * Get artist with given name
     *
     * @param artistName Name of searched artist
     * @return Found artist with given name
     */
    List<Artist> getArtistsByName(String artistName);

    /**
     * Remove given artist
     *
     * @param artist Artist that will be removed from database
     */
    void deleteArtist(Artist artist);

    /**
     * Update artist
     *
     * @param artist Artist that will be updated
     */
    void updateArtistInfo(Artist artist);
}
