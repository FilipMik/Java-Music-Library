package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.data.entities.Genre;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.service.SongService;
import cz.muni.fi.pa165.music_library.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Filip Mik on 24. 11. 2018
 */

@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private SongService songService;

    private Song song;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initSong() {
        song = new Song();
        song.setTitle("song");
    }

    @Test
    public void getTopSongsTest() {
        songService.createSong(song);
        song.addRating(1.0);
        songService.updateSong(song);

        Song song2 = new Song();
        song2.setTitle("song2");
        song2.addRating(4.5);
        songService.createSong(song2);

        Song song3 = new Song();
        song3.setTitle("song3");
        song3.addRating(3.0);
        songService.createSong(song3);

        Assert.assertEquals(songService.getAllTimeTopSongs(5, null).size(), 3);
        Assert.assertEquals(songService.getAllTimeTopSongs(1, null).size(), 1);
        Assert.assertEquals(songService.getAllTimeTopSongs(1, null).get(0).getTitle(), "song2");
    }

    @Test
    public void getTopSongByGenreTest() {
        songService.createSong(song);
        song.addRating(1.0);
        song.setGenre(Genre.ROCK);
        songService.updateSong(song);

        Song song2 = new Song();
        song2.setTitle("song2");
        song2.addRating(4.5);
        song2.setGenre(Genre.ROCK);
        songService.createSong(song2);

        Song song3 = new Song();
        song3.setTitle("song3");
        song3.addRating(3.0);
        song3.setGenre(Genre.POP);
        songService.createSong(song3);
        Assert.assertEquals(songService.getAllTimeTopSongs(5, null).size(), 3);
        Assert.assertEquals(songService.getAllTimeTopSongs(5, Genre.ROCK).size(), 2);

        Assert.assertEquals(
                songService.getAllTimeTopSongs(5, Genre.ROCK).get(0).getTitle(), song2.getTitle());
        Assert.assertEquals(
                songService.getAllTimeTopSongs(5, Genre.ROCK).get(1).getTitle(), song.getTitle());
    }
}
