package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface PlayListDao {

    List<Playlist> getAllPlaylists();

    Playlist getPlaylistById(int playlistId);

    Boolean addPlaylist(Playlist playlist);

    void deletePlaylist(int playlistId);

    void updatePlaylistTitle(int playlistId, String title);

    void addSong(int playlistId, Song song);

    Boolean removeSong(int playlistId, Song song);

    void clearSongs(int playlistId);
}
