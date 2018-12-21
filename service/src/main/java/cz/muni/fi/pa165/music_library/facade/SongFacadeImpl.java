package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.data.entities.Genre;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.dto.GenreDto;
import cz.muni.fi.pa165.music_library.dto.SongDto;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import cz.muni.fi.pa165.music_library.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Filip Mik on 29. 11. 2018
 */

@Service
@Transactional
public class SongFacadeImpl implements SongFacade {

    private static final Integer NORMAL_QUALITY_RATE = 96;   //kbit/s

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private SongService songService;

    @Override
    public List<SongDto> getAllSongs() {
        return beanMappingService.mapTo(songService.getAllSongs(), SongDto.class);
    }

    @Override
    public SongDto findSongById(Long songId) {
        Song song = songService.getSongById(songId);
        return (song == null) ? null : beanMappingService.mapTo(song, SongDto.class);
    }

    @Override
    public List<SongDto> findSongsByTitle(String songTitle) {
        List<Song> songs = songService.getSongsByTitle(songTitle);
        return beanMappingService.mapTo(songs, SongDto.class);
    }

    @Override
    public List<SongDto> findSongsByArtist(String artistName) {
        List<Song> songs = songService.getSongsByArtist(artistName);
        return beanMappingService.mapTo(songs, SongDto.class);
    }

    @Override
    public List<SongDto> findSongsByGenre(GenreDto genreDTO) {
        List<Song> songs = songService.getSongsByGenre(Genre.valueOf(genreDTO.name()));
        return beanMappingService.mapTo(songs, SongDto.class);
    }

    @Override
    public List<SongDto> findSongsByAlbum(String albumTitle) {
        List<Song> songs = songService.getSongsByAlbum(albumTitle);
        return beanMappingService.mapTo(songs, SongDto.class);
    }

    @Override
    public List<SongDto> getAllTimeTopSongs(Integer numberOfSongs, GenreDto genreDTO) {
        List<Song> songs = songService.getAllTimeTopSongs(numberOfSongs, Genre.valueOf(genreDTO.name()));
        return beanMappingService.mapTo(songs, SongDto.class);
    }

    @Override
    public void createSong(SongDto songDto) {
        if (songDto == null) throw new IllegalArgumentException("Can not create null object");
        Song song = beanMappingService.mapTo(songDto, Song.class);
        song.setBitRate(NORMAL_QUALITY_RATE);
        songService.createSong(song);
    }

    @Override
    public void updateSong(SongDto songDto) {
        songService.updateSong(beanMappingService.mapTo(songDto, Song.class));
    }

    @Override
    public void deleteSong(SongDto songDto) {
        songService.deleteSong(beanMappingService.mapTo(songDto, Song.class));
    }
}
