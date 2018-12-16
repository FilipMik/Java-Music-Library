package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.dto.GenreDto;
import cz.muni.fi.pa165.music_library.dto.SongDto;
import cz.muni.fi.pa165.music_library.facade.SongFacade;
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
 * @author Peter Žiška (487569)
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_SONG)
public class SongController {

    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongFacade songFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDto> getAllSongs() {
        logger.debug("GET getAllSongs()");

        return songFacade.getAllSongs();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDto findSongById(@PathVariable("id") Long songId) {
        logger.debug("GET findSongById({})", songId);

        try {
            SongDto songDto = songFacade.findSongById(songId);
            if (songDto != null) {
                return songDto;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{title}/songs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SongDto> findSongsByTitle(@PathVariable("title") String songTitle) {
        logger.debug("GET findSongsByTitle({})", songTitle);

        List<SongDto> songDtos = songFacade.findSongsByTitle(songTitle);
        if (songDtos.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return songDtos;
        }
    }

    @RequestMapping(value = "/{artistName}/artist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SongDto> findSongsByArtist(@PathVariable("artistName") String artistName) {
        logger.debug("GET findSongsByArtist({})", artistName);

        List<SongDto> songDtos = songFacade.findSongsByArtist(artistName);
        if (songDtos.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return songDtos;
        }
    }

    @RequestMapping(value = "/{genre}/genre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SongDto> findSongsByGenre(@PathVariable("genre") String genre) {
        logger.debug("GET findSongsByGenre({})", genre);

        try {
            GenreDto genreDto = GenreDto.valueOf(genre);
            List<SongDto> songDtos = songFacade.findSongsByGenre(genreDto);

            if (songDtos.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                return songDtos;
            }
        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{albumTitle}/album", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SongDto> findSongsByAlbum(@PathVariable("albumTitle") String albumTitle) {
        logger.debug("GET findSongsByAlbum({})", albumTitle);

        List<SongDto> songDtos = songFacade.findSongsByAlbum(albumTitle);

        if (songDtos.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return songDtos;
        }
    }

    @RequestMapping(value = "/{numberOfSongs}/{genre}")
    public List<SongDto> getAllTimeTopSongs(
            @PathVariable("numberOfSongs") Integer numberOfSongs,
            @PathVariable("genre") GenreDto genreDTO) {
        logger.debug("GET getAllTimeTopSongs({})", numberOfSongs, genreDTO);

        List<SongDto> songDto = songFacade.getAllTimeTopSongs(numberOfSongs,genreDTO);

        if (songDto.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return songDto;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createSong(@RequestBody SongDto songDto) {
        logger.debug("POST createSong({})", songDto);

        songFacade.createSong(songDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateSong(@RequestBody SongDto songDto) {
        logger.debug("PUT updateSong({})", songDto);

        songFacade.updateSong(songDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSong(@PathVariable("id") Long id) {
        logger.debug("DELETE deleteSong({})", id);

        SongDto songDto = songFacade.findSongById(id);

        if (songDto != null) {
            songFacade.deleteSong(songDto);
        } else {
            throw new ResourceNotFoundException();
        }
    }

}
