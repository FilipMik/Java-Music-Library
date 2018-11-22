package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.dao.interfaces.*;
import cz.muni.fi.pa165.music_library.data.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Tests for song DAO implementation
 * @author Klara Minsterova
 */

@ContextConfiguration(classes=ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public SongDao songDao;

    @Autowired
    public ArtistDao artistDao;

    @Autowired
    public AlbumDao albumDao;

    private Song song1;
    private Song song2;

    @BeforeMethod
    public void createOrders() {

        song1 = new Song();
        song2 = new Song();

        Artist artist1 = new Artist();
        artist1.setName("The Beatles");
        artistDao.createArtist(artist1);

        Album album1 = new Album();
        album1.setTitle("Abbey Road");
        album1.setReleaseDate(new Date());
        albumDao.createAlbum(album1);

        song1.setTitle("Octopus's Garden");
        song1.setArtist(artist1);
        song1.setAlbum(album1);
        song1.setAlbumPosition(5);
        song1.setGenre(Genre.ROCK);
        song1.setBitRate(320);
        song1.setCommentary("written and sung by Ringo Starr");

        song2.setTitle("Michelle");

        songDao.createSong(song1);
        songDao.createSong(song2);
    }

    /**
     * Test for method getSongsByTitle - checks if the right song is retrieved
     */
    @Test
    public void findByTitle() {
        List<Song> found = songDao.getSongsByTitle("Michelle");
        Assert.assertEquals(found.size(), 1);
        Assert.assertEquals(found.get(0).getTitle(), "Michelle");
    }

    /**
     * Test for method getSongsByTitle - checks if nothing is retrieved for non existing title
     */
    @Test
    public void findByNonExistentTitle() {
        List<Song> found = songDao.getSongsByTitle("Something");
        Assert.assertEquals(found.size(), 0);
    }

    /**
     * Test for method getSongById - checks if the right song is retrieved
     */
    @Test
    public void findById() {
        Song found = songDao.getSongById(song1.getSongId());
        Assert.assertEquals(found.getTitle(), "Octopus's Garden");
        Assert.assertEquals(found.getArtist().getName(), "The Beatles");
        Assert.assertEquals(found.getGenre(), Genre.ROCK);
    }

    /**
     * Test for method getAllSongs - checks if all created songs are found
     */
    @Test
    public void findAll() {
        List<Song> found = songDao.getAllSongs();
        Assert.assertEquals(found.size(), 2);
    }

    /**
     * Test for method deleteSong - checks if the right song is deleted
     */
    @Test
    public void remove() {
        Assert.assertNotNull(songDao.getSongById(song1.getSongId()));
        Assert.assertNotNull(songDao.getSongById(song2.getSongId()));
        songDao.deleteSong(song1);
        Assert.assertNull(songDao.getSongById(song1.getSongId()));
        Assert.assertNotNull(songDao.getSongById(song2.getSongId()));
    }

    /**
     * Test for method createSong - checks if exception is thrown for null title
     */
    @Test(expectedExceptions = PersistenceException.class)
    public void createWithNullTitle(){
        Song song3 = new Song();
        song3.setTitle(null);
        songDao.createSong(song3);
        songDao.getAllSongs();
    }

    /**
     * Test for method createSong - checks if exception is thrown when creating null song
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull(){
        songDao.createSong(null);
    }

    /**
     * Test for method updateSong - checks if song is updated
     */
    @Test
    public void update(){
        song1.setGenre(Genre.POP);
        songDao.updateSong(song1);
        Song found = songDao.getSongById(song1.getSongId());
        Assert.assertEquals(found.getGenre(), Genre.POP);
    }
}
