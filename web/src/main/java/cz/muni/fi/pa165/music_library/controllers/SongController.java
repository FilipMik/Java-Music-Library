package cz.muni.fi.pa165.music_library.controllers;

import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.dto.SongDto;
import cz.muni.fi.pa165.music_library.facade.SongFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Ficko
 */

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongFacade songFacade;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public String allSongs(
            Model model,
            @RequestParam(value="q", defaultValue = "") String q,
            @RequestParam(value="o", defaultValue = "title") String o) {
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
        return "song/allSongs";
    }

}
