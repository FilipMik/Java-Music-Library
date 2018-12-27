package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan Ficko
 */

@RestController
public class MainController {
    
    final static Logger logger = LoggerFactory.getLogger(MainController.class);
    
    /**
     * The main entry point of the REST API
     * Provides access to all the resources with links to resource URIs
     * Can be even extended further so that all the actions on all the resources are available
     * and can be reused in all the controllers (possibly in full HATEOAS style)
     * 
     * @return resources uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();
        
        resourcesMap.put("album_uri", ApiUris.ROOT_URI_ALBUM);
        resourcesMap.put("artist_uri", ApiUris.ROOT_URI_ARTIST);
        resourcesMap.put("playlist_uri", ApiUris.ROOT_URI_PLAYLIST);
        resourcesMap.put("song_uri", ApiUris.ROOT_URI_SONG);
        resourcesMap.put("user_uri", ApiUris.ROOT_URI_USER);

        return resourcesMap;
        
    }
}
