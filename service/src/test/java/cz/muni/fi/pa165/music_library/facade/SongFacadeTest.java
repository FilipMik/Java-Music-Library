package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.base.BaseFacadeTest;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.data.entities.Genre;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.dto.GenreDto;
import cz.muni.fi.pa165.music_library.dto.SongDto;
import cz.muni.fi.pa165.music_library.service.SongService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Jan Ficko
 */

public class SongFacadeTest extends BaseFacadeTest {

    @Autowired
    private SongFacade songFacade;

    @Mock
    private SongService songService;

    private Song song;
    private List<Song> songList = new ArrayList<>();

    private SongDto songDto;
    private List<SongDto> songDtoList = new ArrayList<>();

    private Long songId = 1L;
    private String songTitle = "Title";

    @BeforeClass
    public void initClass() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(songFacade, "songService", songService);
        ReflectionTestUtils.setField(songFacade, "beanMappingService", beanMappingService);
    }

    @BeforeMethod
    public void init() {
        song = new Song();
        song.setSongId(songId);
        song.setTitle(songTitle);

        song.setAlbumPosition(1);
        song.setGenre(Genre.ROCK);
        song.setBitRate(320);
        song.setCommentary("Commentary");
        Album album = new Album();
        album.setTitle("Album Title");
        song.setAlbum(album);
        Artist artist = new Artist();
        artist.setName("Artist");
        song.setArtist(artist);

        songDto = new SongDto();
        songDto.setSongId(songId);
        songDto.setTitle(songTitle);
        songDto.setAlbumPosition(1);
        songDto.setGenre(GenreDto.ROCK);
        songDto.setBitRate(320);
        songDto.setCommentary("Commentary");
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("Album Title");
        songDto.setAlbum(albumDto);
        ArtistDto artistDto = new ArtistDto();
        artistDto.setName("Artist");
        songDto.setArtist(artistDto);

        songList.add(song);
        songDtoList.add(songDto);
    }

    @Test
    public void getAllSongs() {
        when(songService.getAllSongs()).thenReturn(songList);
        when(beanMappingService.mapTo(songList, SongDto.class)).thenReturn(songDtoList);

        List<SongDto> songs = songFacade.getAllSongs();

        assertThat(songs).isNotNull();
        assertThat(song.getTitle()).isEqualTo(songs.get(0).getTitle());
        verify(songService).getAllSongs();
        assertNotEquals(songs.size(), 0);
        assertTrue(songs.contains(songDto));
    }

    @Test
    public void findSongByIdTest() {
        when(beanMappingService.mapTo(song, SongDto.class)).thenReturn(songDto);
        when(songService.getSongById(songId)).thenReturn(song);

        SongDto songDto = songFacade.findSongById(songId);

        assertThat(songDto).isNotNull();
        assertThat(songDto.getSongId()).isEqualTo(songId);
        verify(songService).getSongById(songId);
        verify(beanMappingService).mapTo(song, SongDto.class);
    }

    @Test
    public void findSongsByTitleTest() {
        when(songService.getSongsByTitle(songTitle)).thenReturn(songList);
        when(beanMappingService.mapTo(songList, SongDto.class)).thenReturn(songDtoList);

        List<SongDto> songs = songFacade.findSongsByTitle(songTitle);

        assertThat(songs).isNotNull();
        assertNotEquals(songs.size(), 0);
        assertThat(song.getTitle()).isEqualTo(songs.get(0).getTitle());
        assertTrue(songs.contains(songDto));
        verify(songService).getSongsByTitle(songTitle);
    }

    @Test
    public void findSongsByArtistTest() {
        when(songService.getSongsByArtist("Artist")).thenReturn(songList);
        when(beanMappingService.mapTo(songList, SongDto.class)).thenReturn(songDtoList);

        List<SongDto> songs = songFacade.findSongsByArtist("Artist");

        assertThat(songs).isNotNull();
        assertNotEquals(songs.size(), 0);
        assertThat(song.getArtist().getName()).isEqualTo(songs.get(0).getArtist().getName());
        verify(songService).getSongsByArtist("Artist");
    }

    @Test
    public void findSongsByGenreTest() {
        when(beanMappingService.mapTo(songList, SongDto.class)).thenReturn(songDtoList);
        when(songService.getSongsByGenre(any(Genre.class))).thenReturn(songList);

        List<SongDto> songs = songFacade.findSongsByGenre(GenreDto.ROCK);

        assertThat(songs).isNotNull();
        assertNotEquals(songs.size(), 0);
        verify(songService).getSongsByGenre(any(Genre.class));
    }

    @Test
    public void findSongsByAlbumTest() {
        when(beanMappingService.mapTo(songList, SongDto.class)).thenReturn(songDtoList);
        when(songService.getSongsByAlbum("Album Title")).thenReturn(songList);

        List<SongDto> songs = songFacade.findSongsByAlbum("Album Title");

        assertThat(songs).isNotNull();
        assertNotEquals(songs.size(), 0);
        assertThat(song.getAlbum().getTitle()).isEqualTo(songs.get(0).getAlbum().getTitle());
        verify(songService).getSongsByAlbum("Album Title");
    }

    @Test
    public void getAllTimeTopSongsTest() {
        when(beanMappingService.mapTo(songList, SongDto.class)).thenReturn(songDtoList);
        when(songService.getAllTimeTopSongs(any(), any(Genre.class))).thenReturn(songList);

        List<SongDto> songs = songFacade.getAllTimeTopSongs(1, GenreDto.ROCK);

        assertThat(songs).isNotNull();
        assertNotEquals(songs.size(), 0);
        verify(songService).getAllTimeTopSongs(any(), any(Genre.class));
    }

    @Test
    public void createSongTest() {
        when(beanMappingService.mapTo(songDto, Song.class)).thenReturn(song);

        songFacade.createSong(songDto);

        verify(songService).createSong(song);
        verify(beanMappingService).mapTo(songDto, Song.class);
    }

    @Test
    public void updateSongTest() {
        SongDto songUpdatedDto = songDto;
        songUpdatedDto.setTitle("Updated");
        Song songUpdated = song;
        songUpdated.setTitle("Updated");

        when(beanMappingService.mapTo(songDto, Song.class)).thenReturn(songUpdated);

        songFacade.updateSong(songUpdatedDto);

        verify(songService).updateSong(songUpdated);
        verify(beanMappingService).mapTo(songDto, Song.class);
    }

    @Test
    public void deleteSongTest() {
        when(beanMappingService.mapTo(songDto, Song.class)).thenReturn(song);

        songFacade.deleteSong(songDto);

        verify(songService).deleteSong(song);
    }

}
