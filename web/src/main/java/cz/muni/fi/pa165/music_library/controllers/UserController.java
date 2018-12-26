package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.facade.PlaylistFacade;
import cz.muni.fi.pa165.music_library.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Peter Žiška on 22. 12. 2018
 */

@Controller
@RequestMapping("/user")
public class UserController {

    final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private PlaylistFacade playlistFacade;

    @RequestMapping(value = "/profile/{userId}",  method = RequestMethod.GET)
    public String showProfile(@PathVariable Long userId, Model model) {
        log.debug("Show User Profile: " + userId);

        model.addAttribute("user", userFacade.findUserById(userId));
        model.addAttribute("playlists", playlistFacade.findUserPlaylists(userId));
        return "user/profile";
    }

    @RequestMapping(value = "/profile",  method = RequestMethod.GET)
    public String showAuthProfile(Model model) {
        log.debug("Show User Profile: ");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto authUser = userFacade.findUserByEmail(currentPrincipalName);

        model.addAttribute("user", authUser);
        model.addAttribute("playlists", playlistFacade.findUserPlaylists(authUser.getUserId()));
        return "user/profile";
    }

    @RequestMapping(value = "/all",  method = RequestMethod.GET)
    public String showUsers(Model model) {
        log.debug("showUsers");
        model.addAttribute("users", userFacade.getAllUsers());
        return "user/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes ) {
        UserDto user = userFacade.findUserById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDto authUser = userFacade.findUserByEmail(currentPrincipalName);

        try{
            if (user.equals(authUser)) throw new Exception("Cannot delete yourself");
            userFacade.deleteUser(user);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "User \"" + user.getEmail() + "\" cannot be deleted.");
            return "redirect:" + uriBuilder.path("/user/all").toUriString();
        }
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "User \"" + user.getEmail() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/user/all").toUriString();
    }
}
