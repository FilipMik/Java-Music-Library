package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Filip Mik on 22. 11. 2018
 */

@Service
public interface SongService {

    List<Song> getAllSongs();

    List<Song> getSongsByTitle(String title);

    Song getSongById(Long songId);

    void deleteSong(Song song);

    void updateSong(Song song);

}
