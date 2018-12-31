package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.facade.AlbumFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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

    @RequestMapping("/detail/{albumId}")
    public String showDetail(@PathVariable Long albumId,
                             Model model,
                             UriComponentsBuilder uriBuilder,
                             RedirectAttributes redirectAttributes) {

        AlbumDto albumDto = albumFacade.findAlbumById(albumId);
        if (albumDto == null) {
            redirectAttributes.addFlashAttribute("alert_danger", "Album with number ID: \"" + albumId + "\" not found ");
            return "redirect:" + uriBuilder.path("/album/all").toUriString();
        }

        model.addAttribute("album", albumDto);
        return "album/detail";
    }
}
