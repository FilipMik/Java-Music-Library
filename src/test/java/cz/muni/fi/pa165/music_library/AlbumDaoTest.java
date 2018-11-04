package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jan Ficko
 */

@ContextConfiguration(classes=ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AlbumDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    AlbumDao albumDao;
    @Autowired
    SongDao songDao;

    private Album albumOne;
    private Album albumTwo;

    private Song songOne;
    private Song songTwo;

    /**
     * Instantiate two album objects that can be used throughout this test class.
     */
    @Before
    public void init() {
        albumOne = new Album();
        albumOne.setReleaseDate(new Date());
        albumOne.setTitle("Album One");
        albumOne.setCommentary("Commentary one.");
        albumOne.setAlbumArtUrl("https://album.one");

        albumTwo = new Album();
        albumTwo.setReleaseDate(new Date());
        albumTwo.setTitle("Album Two");
        albumTwo.setCommentary("Commentary two.");
        albumTwo.setAlbumArtUrl("https://album.two");

        songOne = new Song();
        songOne.setTitle("Song One");
        songDao.createSong(songOne);

        songTwo = new Song();
        songTwo.setTitle("Song Two");
        songDao.createSong(songTwo);
    }

    /**
     * Test if album is inserted into the database.
     */
    @Test
    public void testAlbumPersistence() {
        albumDao.createAlbum(albumOne);

        assertEquals("Expected one album", 1, albumDao.getAllAlbums().size());
    }

    /**
     * Test if exception is thrown when null object is inserted.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPersistenceWhenAlbumObjectIsNull_Exception() {
        albumDao.createAlbum(null);

        assertNotEquals("Expected zero albums", 0, albumDao.getAllAlbums().size());
    }

    /**
     * Test if exception is thrown if there is no release date set.
     */
    @Test(expected = PersistenceException.class)
    public void testPersistenceWhenReleaseDateIsNull_Exception() {
        albumOne.setReleaseDate(null);

        albumDao.createAlbum(albumOne);

        assertNotEquals("Expected zero albums", 0, albumDao.getAllAlbums().size());
    }

    /**
     * Test if exception is thrown if there is no title set.
     */
    @Test(expected = PersistenceException.class)
    public void testPersistenceWhenTitleIsNull_Exception() {
        albumOne.setTitle(null);

        albumDao.createAlbum(albumOne);

        assertNotEquals("Expected zero albums", 0, albumDao.getAllAlbums().size());
    }

    /**
     * Test if album object is inserted even though the song is null.
     */
    @Test
    public void testPersistenceWhenPlaylistIsNull() {
        albumOne.addSong(null);

        albumDao.createAlbum(albumOne);

        assertEquals("Expected one album", 1, albumDao.getAllAlbums().size());
    }

    /**
     * Test if both objects, that are inserted, are retrieved when method getAllAlbums is called.
     */
    @Test
    public void testGetAllAlbums_EqualsTwo() {
        Date currentDate = new Date();
        Date tomorrowsDate = new Date(System.currentTimeMillis() + 86400000);

        albumOne.setReleaseDate(currentDate);
        albumDao.createAlbum(albumOne);
        albumTwo.setReleaseDate(tomorrowsDate);
        albumDao.createAlbum(albumTwo);

        List<Album> albums = albumDao.getAllAlbums();

        assertEquals("Expected two albums", 2, albums.size());

        assertEquals(albums.get(0).getReleaseDate(), currentDate);
        assertEquals(albums.get(0).getTitle(), "Album One");
        assertEquals(albums.get(0).getCommentary(), "Commentary one.");
        assertEquals(albums.get(0).getAlbumArtUrl(), "https://album.one");
        assertEquals(albums.get(1).getReleaseDate(), tomorrowsDate);
        assertEquals(albums.get(1).getTitle(), "Album Two");
        assertEquals(albums.get(1).getCommentary(), "Commentary two.");
        assertEquals(albums.get(1).getAlbumArtUrl(), "https://album.two");
    }

    /**
     * Test if you get the right album object from the getAlbumById method.
     */
    @Test
    public void testGetAlbumById() {
        albumDao.createAlbum(albumOne);

        List<Album> albumList = albumDao.getAllAlbums();
        assertEquals("Expected one album", 1, albumList.size());

        Album retrievedAlbum = albumDao.getAlbumById(albumList.get(0).getAlbumId());

        assertNotNull("Expected to retrieve album by ID", retrievedAlbum);
    }

    /**
     * Test if you get the right album object from the getAlbumByTitle method.
     */
    @Test
    public void testGetAlbumByTitle() {
        albumDao.createAlbum(albumOne);

        List<Album> retrievedAlbum = albumDao.getAlbumByTitle("Album One");

        assertNotNull("Expected to retrieve at least one album", retrievedAlbum);
    }
    /**
     * Test if exception is thrown when trying to delete null album object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAlbumWhenAlbumObjectIsNull_Exception() {
        albumDao.deleteAlbum(null);
    }

    /**
     * Test if album object is deleted from the database.
     */
    @Test
    public void testDeleteOneAlbum() {
        albumDao.createAlbum(albumOne);

        List<Album> albumList = albumDao.getAllAlbums();
        assertEquals("Expected one album", 1, albumList.size());

        Album retrievedAlbum = albumDao.getAlbumById(albumList.get(0).getAlbumId());
        assertEquals(albumOne.getTitle(), retrievedAlbum.getTitle());

        albumDao.deleteAlbum(retrievedAlbum);

        List<Album> newAlbumList = albumDao.getAllAlbums();
        assertEquals("Expected zero albums", 0, newAlbumList.size());
    }

    /**
     * Test if the right album object is deleted when there are two albums in the database.
     */
    @Test
    public void testDeleteOneAlbumWithTwoInDatabase() {
        albumDao.createAlbum(albumOne);
        albumDao.createAlbum(albumTwo);

        List<Album> albumList = albumDao.getAllAlbums();
        assertEquals("Expected two albums", 2, albumList.size());

        Album retrievedAlbum = albumDao.getAlbumById(albumList.get(1).getAlbumId());
        assertEquals(albumTwo.getTitle(), retrievedAlbum.getTitle());

        albumDao.deleteAlbum(retrievedAlbum);

        List<Album> newAlbumList = albumDao.getAllAlbums();
        assertEquals("Expected one album", 1, newAlbumList.size());

        assertEquals(albumOne.getTitle(), newAlbumList.get(0).getTitle());
    }
    /**
     * Test if album data is updated when using update method.
     */
    @Test
    public void testUpdateAlbum() {
        albumDao.createAlbum(albumOne);
        albumDao.createAlbum(albumTwo);

        List<Album> firstAlbumRetrieve = albumDao.getAlbumByTitle("Album One");
        firstAlbumRetrieve.get(0).setTitle("Masarykova");

        albumDao.updateAlbum(firstAlbumRetrieve.get(0));

        List<Album> albumList = albumDao.getAllAlbums();
        assertEquals("Expected two albums", 2, albumList.size());

        assertEquals(firstAlbumRetrieve.get(0).getAlbumId(), albumList.get(0).getAlbumId());
    }

    /**
     * Test that one to many relation between Song and Album is correct.
     */
    @Test
    public void testAlbumAndSongRelation() {
        albumOne.addSong(songOne);
        albumOne.addSong(songTwo);
        songOne.setAlbum(albumOne);
        songTwo.setAlbum(albumOne);

        songDao.createSong(songOne);
        songDao.createSong(songTwo);
        albumDao.createAlbum(albumOne);

        Album retrievedAlbum = albumDao.getAlbumById(albumOne.getAlbumId());
        assertEquals("Expected 2 songs in album", 2L, retrievedAlbum.getSongList().size());

        Song retrievedSongOne = retrievedAlbum.getSongList().get(0);
        Song retrievedSongTwo = retrievedAlbum.getSongList().get(1);
        assertEquals("Expected reference to first song", songOne, retrievedSongOne);
        assertEquals("Expected reference to second song", songTwo, retrievedSongTwo);

        Song retrievedSong = songDao.getSongById(songOne.getSongId());
        assertEquals( "Album One", retrievedSong.getAlbum().getTitle());
    }

}
