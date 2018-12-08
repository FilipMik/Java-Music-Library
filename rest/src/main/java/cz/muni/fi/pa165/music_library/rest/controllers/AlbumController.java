package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.facade.AlbumFacade;
import cz.muni.fi.pa165.music_library.rest.ApiUris;
import cz.muni.fi.pa165.music_library.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(ApiUris.ROOT_URI_ALBUM)
public class AlbumController {

    final static Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumFacade albumFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<AlbumDto> getAllAlbums() {
        logger.debug("GET getAlbums()");

        return albumFacade.getAllAlbums();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AlbumDto findAlbumById(@PathVariable("id") Long id) throws Exception {
        logger.debug("GET getAlbum({})", id);

        try {
            AlbumDto albumDto = albumFacade.findAlbumById(id);
            return albumDto;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArtistDto findAlbumArtist(Long albumId) {
        return null;
    }

    public List<AlbumDto> findAlbumsByTitle(String title) {
        return null;
    }

    public List<AlbumDto> findAlbumsByArtist(String artistName) {
        return null;
    }

    public List<AlbumDto> findAlbumsByArtistId(Long artistId) {
        return null;
    }

    public List<AlbumDto> getLastWeekAlbums() {
        return null;
    }

    public void createAlbum(AlbumDto album) {

    }

    public void updateAlbum(AlbumDto album) {

    }

    public void addSongs(Long albumId, List<Long> songIds) {

    }

    public void addSong(Long albumId, Long songId) {

    }

    public void removeSong(Long albumId, Long songId) {

    }

    public void deleteAlbum(Long albumId) {

    }



}
