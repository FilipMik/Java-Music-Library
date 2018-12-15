package cz.muni.fi.pa165.music_library.rest.controllers;


import cz.muni.fi.pa165.music_library.data.entities.Genre;
import cz.muni.fi.pa165.music_library.dto.PlaylistDto;
import cz.muni.fi.pa165.music_library.facade.PlaylistFacade;
import cz.muni.fi.pa165.music_library.rest.ApiUris;
import cz.muni.fi.pa165.music_library.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_PLAYLIST)
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistFacade playlistFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlaylistDto> getAllPlaylists() {
        logger.debug("GET getAllPlaylists");

        return playlistFacade.getAllPlaylists();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlaylistDto findPlaylistById(@PathVariable("id") Long playlistId) {
        logger.debug("GET findPlaylistById");

        PlaylistDto playlistDto = playlistFacade.findPlaylistById(playlistId);

        if (playlistDto == null) {
            throw new ResourceNotFoundException();
        } else {
            return playlistDto;
        }
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlaylistDto> findPlaylistByTitle(@PathVariable("title") String playlistTitle) {
        logger.debug("GET findPlaylistsByTitle");

        return playlistFacade.findPlaylistsByTitle(playlistTitle);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlaylistDto> findUserPlaylists(@PathVariable("userId") Long userId) {
        logger.debug("GET findUsersPlaylists");

        return playlistFacade.findUserPlaylists(userId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void createPlaylist(@RequestBody PlaylistDto playlistDto) {
        logger.debug("POST createPlaylist");

        playlistFacade.createPlaylist(playlistDto);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void updatePlaylist(@RequestBody PlaylistDto playlistDto) {
        logger.debug("PUT updatePlaylist");

        playlistFacade.updatePlaylist(playlistDto);
    }

    @RequestMapping(value = "/{id}/song/{songId}", method = RequestMethod.POST)
    public final void addSong(@PathVariable("id") Long playlistId, @PathVariable("songId") Long songId) {
        logger.debug("POST addSong");

        playlistFacade.addSong(playlistId, songId);
    }

    @RequestMapping(value = "/{id}/songs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void addSongs(@PathVariable("id") Long playlistId, @RequestBody List<Long> songIds) {
        logger.debug("POST addSongs");

        playlistFacade.addSongs(playlistId, songIds);
    }

    @RequestMapping(value = "/{id}/song/{songId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void removeSong(@PathVariable("id") Long playlistId, @PathVariable("songId") Long songId) {
        logger.debug("DELETE removeSong");

        playlistFacade.removeSong(playlistId, songId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deletePlaylist(@PathVariable("id") Long playlistId) {
        logger.debug("DELETE removePlaylist");

        PlaylistDto playlistDto = playlistFacade.findPlaylistById(playlistId);

        if (playlistDto == null) {
            throw new ResourceNotFoundException();
        } else {
            playlistFacade.deletePlaylist(playlistDto);
        }
    }
}
