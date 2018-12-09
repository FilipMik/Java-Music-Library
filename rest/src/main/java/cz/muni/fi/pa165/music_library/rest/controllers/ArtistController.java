package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.facade.ArtistFacade;
import cz.muni.fi.pa165.music_library.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUris.ROOT_URI_ARTIST)
public class ArtistController {

    private final static Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistFacade artistFacade;

}
