package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Song;

import java.awt.*;
import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface AlbumDao {

    List<Album> getAllALbums();

    Album getAlbumById(int albumId);

    Album getAlbumByTitle(int title);

    Boolean addAlbum(Album album);

    void deleteAlbum(int albumId);

    void updateAlbumCommentary(int albumId, String commentary);

    void updateAlbumArt(int albumId, Image albumArt);

    void updateAlbumSongs(int albumId, List<Song> songs);
}
