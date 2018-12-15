package cz.muni.fi.pa165.music_library.rest.controllers;


import cz.muni.fi.pa165.music_library.facade.PlaylistFacade;
import cz.muni.fi.pa165.music_library.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUris.ROOT_URI_PLAYLIST)
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistFacade playlistFacade;


}
