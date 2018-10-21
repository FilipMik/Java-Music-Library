package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.Album;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface AlbumDao {

    List<Album> getAllALbums();

    Album getAlbum(int albumId);

    Album getAlbumByTitle(int title);

    void addAlbum(Album album);

    void deleteAlbum(int albumId);

    void updateAlbum(int albumId);

}
