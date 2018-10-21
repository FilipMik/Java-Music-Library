package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Artist;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface ArtistDao {

    List<Artist> getAllArtists();

    Artist getArtistById(int artistId);

    Artist getArtistByName(int artistName);

    Boolean AddArtist(Artist artist);

    void deleteArtist();

    void updateArtistInfo(int artistId, String info);

    void updateArtistSongsCreated(int artistId, int songsCreated);
}
