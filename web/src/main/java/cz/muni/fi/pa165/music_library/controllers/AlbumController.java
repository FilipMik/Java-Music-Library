package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.facade.AlbumFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Peter Žiška on 28. 12. 2018
 */

@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumFacade albumFacade;

    @RequestMapping("/all")
    public String allArtists(Model model) {
        model.addAttribute("albums", albumFacade.getAllAlbums());
        return "album/list";
    }
}
