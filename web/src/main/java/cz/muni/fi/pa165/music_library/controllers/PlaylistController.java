package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.facade.PlaylistFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistFacade playlistFacade;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String allArtists(Model model) {
        model.addAttribute("playlists", playlistFacade.getAllPlaylists());
        return "playlist/allPlaylists";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("playlist", playlistFacade.findPlaylistById(id));
        return "playlist/detailPlaylist";
    }
}

