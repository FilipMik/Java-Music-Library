package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.service.ArtistService;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 */

@Service
@Transactional
public class ArtistFacadeImpl implements ArtistFacade {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<ArtistDto> getAllArtists() {
        List<Artist> artists = artistService.getAllArtists();
        return (artists == null) ? null : beanMappingService.mapTo(artists,ArtistDto.class);
    }

    @Override
    public ArtistDto findArtistById(Long artistId) {
        Artist artist = artistService.getArtistById(artistId);
        return (artist == null) ? null : beanMappingService.mapTo(artist,ArtistDto.class);
    }

    @Override
    public List<ArtistDto> findArtistsByName(String name) {
        List<Artist> artists = artistService.getArtistsByName(name);
        return (artists == null) ? null : beanMappingService.mapTo(artists,ArtistDto.class);
    }

    @Override
    public void createArtist(ArtistDto artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Album DTO shouldn't be null");
        }
        Artist artistCreate = beanMappingService.mapTo(artist,Artist.class);
        artistService.createArtist(artistCreate);
    }

    @Override
    public void updateArtist(ArtistDto artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Album DTO shouldn't be null");
        }
        Artist artistUpdate = beanMappingService.mapTo(artist,Artist.class);
        artistService.updateArtistInfo(artistUpdate);
    }

    @Override
    public void deleteArtist(ArtistDto artist) {
        artistService.deleteArtist(beanMappingService.mapTo(artist, Artist.class));
    }
}
