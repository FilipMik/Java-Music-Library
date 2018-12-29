package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.dto.PlaylistDto;
import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.facade.PlaylistFacade;
import cz.muni.fi.pa165.music_library.facade.SongFacade;
import cz.muni.fi.pa165.music_library.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.util.UriComponentsBuilder;

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

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String allPlaylists(Model model) {
        model.addAttribute("playlists", playlistFacade.getAllPlaylists());
        return "playlist/allPlaylists";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        log.debug("detail({})", id);
        model.addAttribute("playlist", playlistFacade.findPlaylistById(id));
        model.addAttribute("authUser", getAuthUser());
        return "playlist/detailPlaylist";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPlaylist(Model model) {
        model.addAttribute("playlist", new PlaylistDto());
        return "playlist/newPlaylist";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPlaylist(@Valid @ModelAttribute("playlist") PlaylistDto formBean, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes) {
        formBean.setUser(getAuthUser());
        log.debug("create(playlist={})", formBean);

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

        redirectAttributes.addFlashAttribute("alert_success", "Playlist was created.");

        playlistFacade.createPlaylist(formBean);
        return "redirect:/playlist/all";
    }

    @RequestMapping(value = "/remove/{playlistId}/song/{songId}", method = RequestMethod.GET)
    public String removeSong(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") Long songId,
                       UriComponentsBuilder uriBuilder) {
        log.debug("remove({})", songId);
        playlistFacade.removeSong(playlistId, songId);
        return "redirect:" + uriBuilder.path("/playlist/detail/" + playlistId).toUriString();
    }

    private UserDto getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userFacade.findUserByEmail(authentication.getName());
    }
}

