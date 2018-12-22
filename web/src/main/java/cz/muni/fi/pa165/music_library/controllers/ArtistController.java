package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.facade.ArtistFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Filip Mik on 22. 12. 2018
 */

@Controller
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistFacade artistFacade;

    @RequestMapping("/all")
    public String allArtists(Model model) {
        model.addAttribute("artists", artistFacade.getAllArtists());
        return "artist/allArtists";
    }
}
