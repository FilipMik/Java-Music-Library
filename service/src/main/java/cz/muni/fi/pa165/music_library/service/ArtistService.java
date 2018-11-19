package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.data.entities.Artist;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

public interface ArtistService {

    /**
     * Creates artist
     *
     * @param artist Artist to be created
     */
    void createArtist(Artist artist);

    /**
     * Return every artist stored in DB
     *
     * @return List of Artists in DB
     */
    List<Artist> getAllArtists();

    /**
     * Returns list of albums with specified ID
     *
     * @param artistId Id of Artist
     * @return Artist with artist ID (unique)
     */
    Artist getArtistById(Long artistId);

    /**
     * Returns list of artists with specified name
     *
     * @param artistName Name of artists to be found
     * @return List of Artists with specified title
     */
    List<Artist> getArtistsByName(String artistName);

    /**
     * Deletes specified artist
     *
     * @param artist Artist to be deleted
     */
    void deleteArtist(Artist artist);

    /**
     * Updates specified artist information
     *
     * @param artist Artist to be updated
     */
    void updateArtistInfo(Artist artist);
}
