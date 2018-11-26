package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.dto.Genre;
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

    List<SongDto> findSongsByGenre(Genre genre);

    List<SongDto> findSongsByAlbum(String albumTitle);

    void createSong(SongDto song);

    void deleteSong(Long songId);

}
