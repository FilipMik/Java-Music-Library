package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private ArtistDao artistDao;

    @Override
    public void createArtist(Artist artist) {
        artistDao.createArtist(artist);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistDao.getAllArtists();
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return artistDao.getArtistById(artistId);
    }

    @Override
    public List<Artist> getArtistsByName(String artistName) {
        return artistDao.getArtistsByName(artistName);
    }

    @Override
    public void deleteArtist(Artist artist) {
        artistDao.deleteArtist(artist);
    }

    @Override
    public void updateArtistInfo(Artist artist) {
        artistDao.updateArtistInfo(artist);
    }
}
