package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.facade.PlaylistFacade;
import cz.muni.fi.pa165.music_library.facade.SongFacade;
import cz.muni.fi.pa165.music_library.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jan Ficko
 */

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongFacade songFacade;
    @Autowired
    private PlaylistFacade playlistFacade;
    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String allSongs(
            Model model,
            @RequestParam(value="q", defaultValue = "") String q,
            @RequestParam(value="o", defaultValue = "title") String o,
            @RequestParam(value="s", defaultValue = "") String songId,
            @RequestParam(value="p", defaultValue = "") String playlistId) {

        model.addAttribute("user", getAuthUser());
        if(q.equals("")) {
            model.addAttribute("songs", songFacade.getAllSongs());
        } else {
            model.addAttribute("q", q);
            model.addAttribute("o", o);
            switch(o) {
                case "title":
                    model.addAttribute("songs", songFacade.findSongsByTitle(q));
                    break;
                case "artist":
                    model.addAttribute("songs", songFacade.findSongsByArtist(q));
                    break;
                case "album":
                    model.addAttribute("songs", songFacade.findSongsByAlbum(q));
                    break;
            }
        }

        if(!songId.equals("") && !playlistId.equals("")) {
            try {
                playlistFacade.addSong(Long.valueOf(playlistId), Long.valueOf(songId));
            } catch(Exception ignored) { }
        }
        return "song/allSongs";
    }

    private UserDto getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userFacade.findUserByEmail(authentication.getName());
    }

}
