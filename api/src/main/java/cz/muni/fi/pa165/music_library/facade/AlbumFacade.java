package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;

import java.util.List;

/**
 * @author Jan Ficko
 */
public interface AlbumFacade {

    List<AlbumDto> getAllAlbums();

    AlbumDto findAlbumById(Long albumId);

    List<ArtistDto> findArtistsFromAlbum(Long albumId);

    List<AlbumDto> findAlbumsByTitle(String title);

    List<AlbumDto> findAlbumsByArtist(String artistName);

    List<AlbumDto> getLastWeekAlbums();

    void createAlbum(AlbumDto album);

    void updateAlbum(AlbumDto album);

    void addSongs(Long albumId, Long[] songIds);

    void addSong(Long albumId, Long songId);

    void removeSong(Long albumId, Long songId);

    void deleteAlbum(Long albumId);

}
