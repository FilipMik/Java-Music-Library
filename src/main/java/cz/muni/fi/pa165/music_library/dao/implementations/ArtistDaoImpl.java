package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.data.entities.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author //TODO your name here
 */

public class ArtistDaoImpl implements ArtistDao {

    @Override
    public void createArtist(Artist artist) {
        //TODO implement
    }

    @Override
    public List<Artist> getAllArtists() {
        //TODO implement
        return new ArrayList<>();
    }

    @Override
    public Artist getArtistById(Long artistId) {
        //TODO implement
        return null;
    }

    @Override
    public List<Artist> getArtistByName(String artistName) {
        //TODO implement
        return null;
    }

    @Override
    public void deleteArtist(Artist artist) {
        //TODO implement
    }

    @Override
    public void updateArtistInfo(Artist artist) {
        //TODO implement
    }

}
