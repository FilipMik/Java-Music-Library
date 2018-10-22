package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.data.entities.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public class ArtistDaoImpl implements ArtistDao {

    private List<Artist> artists = new ArrayList<>();

    public ArtistDaoImpl() {
        // TODO implement
    }

    @Override
    public List<Artist> getAllArtists() {
        return artists;
    }

    @Override
    public Artist getArtistById(int artistId) {
        // TODO implement
        return null;
    }

    @Override
    public Artist getArtistByName(int artistName) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean addArtist(Artist artist) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean deleteArtist(int artistId) {
        //TODO implement
        return null;
    }

    @Override
    public Boolean updateArtistInfo(int artistId, String info) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean updateArtistSongsCreated(int artistId, int songsCreated) {
        // TODO implement
        return null;
    }
}
