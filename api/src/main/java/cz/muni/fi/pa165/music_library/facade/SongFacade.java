package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.dto.GenreDto;
import cz.muni.fi.pa165.music_library.dto.SongDto;

import java.util.List;

/**
 * @author Jan Ficko
 */
public interface SongFacade {

    List<SongDto> getAllSongs();

    SongDto findSongById(Long songId);

    List<SongDto> findSongsByTitle(String songTitle);

    List<SongDto> findSongsByArtist(String artistName);

    List<SongDto> findSongsByGenre(GenreDto genreDTO);

    List<SongDto> findSongsByAlbum(String albumTitle);

    List<SongDto> getAllTimeTopSongs(Integer numberOfSongs, GenreDto genreDTO);

    void createSong(SongDto song);

    void updateSong(SongDto song);

    void deleteSong(SongDto song);

}
