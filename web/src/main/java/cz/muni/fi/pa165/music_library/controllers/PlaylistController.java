package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.dto.PlaylistDto;
import cz.muni.fi.pa165.music_library.facade.PlaylistFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author Klara Minsterova
 */

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    private final static Logger log = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistFacade playlistFacade;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String allArtists(Model model) {
        model.addAttribute("playlists", playlistFacade.getAllPlaylists());
        return "playlist/allPlaylists";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        log.debug("detail({})", id);
        model.addAttribute("playlist", playlistFacade.findPlaylistById(id));
        return "playlist/detailPlaylist";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProduct(Model model) {
        PlaylistDto playlistDto = new PlaylistDto();
        playlistDto.setDateCreated(new Date());
        //TODO get logged user
        model.addAttribute("playlist", playlistDto);
        return "playlist/newPlaylist";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("playlist") PlaylistDto formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes) {
        log.debug("create(productCreate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "playlist/newPlaylist";
        }

        playlistFacade.createPlaylist(formBean);
        return "redirect:/playlist/all";
    }
}

