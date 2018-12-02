package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Genre;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.service.SongService;
import cz.muni.fi.pa165.music_library.service.SongServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for Song service implementation.
 * @author Klara Minsterova
 */

@ContextConfiguration(classes = ServiceConfig.class)
public class SongServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SongDao songDao;

    @Autowired
    @InjectMocks
    private SongService songService;

    private Song song1;
    private Song song2;

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        songService = new SongServiceImpl(songDao);
    }

    @BeforeMethod
    public void initSong() {
        song1 = new Song();
        song1.setSongId(1l);
        song1.setTitle("Octopus's Garden");

        song2 = new Song();
        song2.setTitle("Michelle");
    }

    @Test
    public void testGetAllSongs() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getAllSongs()).thenReturn(allSongs);
        List<Song> result = songService.getAllSongs();

        verify(songDao).getAllSongs();
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetSongsByArtist() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getSongsByArtist(anyString())).thenReturn(allSongs);
        List<Song> result = songService.getSongsByArtist("artist");

        verify(songDao).getSongsByArtist("artist");
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetSongsByTitle() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getSongsByTitle(anyString())).thenReturn(allSongs);
        List<Song> result = songService.getSongsByTitle("title");

        verify(songDao).getSongsByTitle("title");
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetSongsByGenre() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getSongsByGenre(anyObject())).thenReturn(allSongs);
        List<Song> result = songService.getSongsByGenre(Genre.ROCK);

        verify(songDao).getSongsByGenre(Genre.ROCK);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetSongsByAlbum() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getSongsByAlbum(anyString())).thenReturn(allSongs);
        List<Song> result = songService.getSongsByAlbum("album");

        verify(songDao).getSongsByAlbum("album");
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetAllTimeTopSongsNoConditions() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getAllSongsByRating(null)).thenReturn(allSongs);
        List<Song> result = songService.getAllTimeTopSongs(null, null);

        verify(songDao).getAllSongsByRating(null);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetAllTimeTopSongsLimitedNumber() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getAllSongsByRating(null)).thenReturn(allSongs);
        List<Song> result = songService.getAllTimeTopSongs(1, null);

        verify(songDao).getAllSongsByRating(null);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetAllTimeTopSongsLimitedGenre() {
        List<Song> allSongs = Arrays.asList(song1, song2);
        when(songDao.getAllSongsByRating(anyObject())).thenReturn(allSongs);
        List<Song> result = songService.getAllTimeTopSongs(null, Genre.ROCK);

        verify(songDao).getAllSongsByRating(Genre.ROCK);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), song1);
    }

    @Test
    public void testGetSongById() {
        when(songDao.getSongById(song1.getSongId())).thenReturn(song1);
        Song result = songService.getSongById(1l);

        verify(songDao).getSongById(1l);
        Assert.assertEquals(result, song1);
    }

    @Test
    public void testCreateSong() {
        songService.createSong(song1);
        verify(songDao).createSong(song1);
    }

    @Test
    public void testDeleteSong() {
        songService.deleteSong(song1);
        verify(songDao).deleteSong(song1);
    }

    @Test
    public void testUpdateSong() {
        songService.updateSong(song1);
        verify(songDao).updateSong(song1);
    }
}
