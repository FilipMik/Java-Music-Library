package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.facade.AlbumFacade;
import cz.muni.fi.pa165.music_library.facade.ArtistFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Filip Mik on 22. 12. 2018
 */

@Controller
@RequestMapping("/artist")
public class ArtistController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ArtistFacade artistFacade;

    @Autowired
    private AlbumFacade albumFacade;

    @RequestMapping("/all")
    public String allArtists(Model model) {
        model.addAttribute("artists", artistFacade.getAllArtists());
        return "artist/allArtists";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        log.debug("detail({})", id);
        model.addAttribute("artist", artistFacade.findArtistById(id));
        model.addAttribute("albums", albumFacade.findAlbumsByArtistId(id));
        return "artist/detailArtist";
    }
}
