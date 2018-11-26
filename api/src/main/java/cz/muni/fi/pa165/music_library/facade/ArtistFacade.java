package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.dto.ArtistDto;

import java.util.List;

/**
 * @author Jan Ficko
 */
public interface ArtistFacade {

    List<ArtistDto> getAllArtists();

    ArtistDto findArtistById(Long artistId);

    List<ArtistDto> findArtistsByName(String name);

    void createArtist(ArtistDto artist);

    void deleteArtist(Long artistId);

}
