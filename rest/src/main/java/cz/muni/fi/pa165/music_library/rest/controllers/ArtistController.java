package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.facade.ArtistFacade;
import cz.muni.fi.pa165.music_library.rest.ApiUris;
import cz.muni.fi.pa165.music_library.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Filip Mik, 487593
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_ARTIST)
public class ArtistController {

    private final static Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistFacade artistFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ArtistDto> getAllArtists() {
        logger.debug("GET getAllArtists");

        return artistFacade.getAllArtists();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ArtistDto findArtistById(@PathVariable("id") Long artistId) {
        logger.debug("GET findArtistById");

        ArtistDto artistDto = artistFacade.findArtistById(artistId);
        if (artistDto != null) {
            return artistDto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ArtistDto> findArtistsByName(@PathVariable("name") String artistName) {
        logger.debug("GET findArtistByName");

        return artistFacade.findArtistsByName(artistName);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void createArtist(@RequestBody ArtistDto artistDto) {
        logger.debug("POST createAlbum");

        artistFacade.createArtist(artistDto);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void updateArtist(@RequestBody ArtistDto artistDto) {
        logger.debug("PUT updateArtist");

        artistFacade.updateArtist(artistDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteArtist(@PathVariable("id") Long artistId) {
        logger.debug("DELETE deleteAlbum");

        ArtistDto artistDto = artistFacade.findArtistById(artistId);

        if (artistDto == null) {
            throw new ResourceNotFoundException();
        } else {
            artistFacade.deleteArtist(artistDto);
        }
    }
}
