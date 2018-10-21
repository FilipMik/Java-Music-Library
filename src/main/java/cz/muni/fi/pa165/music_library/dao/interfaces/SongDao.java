package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface SongDao {

    List<Song> getAllSongs();

    Song getSongById(int songId);

    Boolean AddSong(Song song);

    void deleteSong();

    void updateSongCommentary(int songId, String commentary);
}
