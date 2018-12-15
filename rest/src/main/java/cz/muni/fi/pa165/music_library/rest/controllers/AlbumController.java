package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.facade.AlbumFacade;
import cz.muni.fi.pa165.music_library.rest.ApiUris;
import cz.muni.fi.pa165.music_library.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.music_library.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jan Ficko
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_ALBUM)
public class AlbumController {

    private final static Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumFacade albumFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AlbumDto> getAllAlbums() {
        logger.debug("GET getAlbums()");

        return albumFacade.getAllAlbums();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AlbumDto findAlbumById(@PathVariable("id") Long albumId)  {
        logger.debug("GET findAlbumById({})", albumId);

        try {
            AlbumDto albumDto = albumFacade.findAlbumById(albumId);

            if (albumDto != null) {
                return albumDto;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch(Exception e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/artists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArtistDto> findAlbumArtists(@PathVariable("id") Long albumId) {
        logger.debug("GET findAlbumArtists({})", albumId);

        List<ArtistDto> listArtistDto = albumFacade.findAlbumArtists(albumId);

        if(listArtistDto != null && !listArtistDto.isEmpty()) {
            return listArtistDto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/artist/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlbumDto> findAlbumsByArtist(@PathVariable("name") String artistName) {
        logger.debug("GET findAlbumsByArtist({})", artistName);

        List<AlbumDto> listAlbumDto = albumFacade.findAlbumsByArtist(artistName);

        if(listAlbumDto != null && !listAlbumDto.isEmpty()) {
            return listAlbumDto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/artist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlbumDto> findAlbumsByArtistId(@PathVariable("id") Long artistId) {
        logger.debug("GET findAlbumsByArtistId({})", artistId);

        List<AlbumDto> listAlbumDto = albumFacade.findAlbumsByArtistId(artistId);

        if (listAlbumDto != null && !listAlbumDto.isEmpty()) {
            return listAlbumDto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/lastweek", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlbumDto> getLastWeekAlbums() {
        logger.debug("GET getLastWeekAlbums()");

        return albumFacade.getLastWeekAlbums();
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AlbumDto> findAlbumsByTitle(@PathVariable("title") String title) {
        logger.debug("GET findAlbumsByTitle({})", title);

        try {
            List<AlbumDto> listAlbumDto = albumFacade.findAlbumsByTitle(title);

            if (listAlbumDto != null && !listAlbumDto.isEmpty()) {
                return listAlbumDto;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch(Exception e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createAlbum(@RequestBody AlbumDto album) {
        logger.debug("POST createAlbum({})", album);

        albumFacade.createAlbum(album);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAlbum(@RequestBody AlbumDto album) {
        logger.debug("PUT updateAlbum({})", album);

        albumFacade.updateAlbum(album);
    }

    @RequestMapping(value = "/{id}/song", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addSongs(@PathVariable("id") Long albumId, @RequestBody List<Long> songIds) {
        logger.debug("POST addSongs({})", albumId, songIds);

        albumFacade.addSongs(albumId, songIds);
    }

    @RequestMapping(value = "/{albumId}/song/{songId}", method = RequestMethod.POST)
    public void addSong(@PathVariable("albumId") Long albumId, @PathVariable("songId") Long songId) {
        logger.debug("GET addSong({})", albumId, songId);

        albumFacade.addSong(albumId, songId);
    }

    @RequestMapping(value = "/{albumId}/song/{songId}", method = RequestMethod.DELETE)
    public void removeSong(@PathVariable("albumId") Long albumId, @PathVariable("songId") Long songId) {
        logger.debug("DELETE removeSong({})", albumId, songId);

        albumFacade.removeSong(albumId, songId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAlbum(@PathVariable("id") Long id) {
        logger.debug("DELETE deleteAlbum({})", id);

        AlbumDto albumDto = albumFacade.findAlbumById(id);

        if (albumDto != null) {
            albumFacade.deleteAlbum(albumDto);
        } else {
            throw new ResourceNotFoundException();
        }
    }

}
