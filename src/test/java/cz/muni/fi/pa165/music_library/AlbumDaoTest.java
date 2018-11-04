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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

        songOne.setTitle("Song One");
        songDao.createSong(songOne);

        songTwo.setTitle("Song Two");
        songDao.createSong(songTwo);
    }

    /**
     * Test if album is inserted into the database.
     */
    @Test
    public void testAlbumPersistence() {
        albumOne.addSong(songDao.getSongByTitle("Song One").get(0));

        albumDao.createAlbum(albumOne);
        if (albumDao.getAllAlbums().size() != 1)
            fail("Expected one album");
    }

    /**
     * Test if exception is thrown when null object is inserted.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPersistenceWhenAlbumObjectIsNull_Exception() {
        albumDao.createAlbum(null);
    }

    /**
     * Test if exception is thrown if there is no release date set.
     */
    @Test(expected = PersistenceException.class)
    public void testPersistenceWhenReleaseDateIsNull_Exception() {
        albumOne.setReleaseDate(null);

        albumDao.createAlbum(albumOne);

        if (albumDao.getAllAlbums().size() != 0)
            fail("Expected zero albums");
    }

    /**
     * Test if exception is thrown if there is no title set.
     */
    @Test(expected = PersistenceException.class)
    public void testPersistenceWhenTitleIsNull_Exception() {
        albumOne.setTitle(null);

        albumDao.createAlbum(albumOne);

        if (albumDao.getAllAlbums().size() != 0)
            fail("Expected zero albums");
    }

    /**
     * Test if album object is inserted even though the song is null.
     */
    @Test
    public void testPersistenceWhenPlaylistIsNull() {
        albumOne.addSong(null);

        albumDao.createAlbum(albumOne);

        if (albumDao.getAllAlbums().size() != 1)
            fail("Expected one album");
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
        if (albums.size() != 2)
            fail("Expected two albums");

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
        if (albumList.size() != 1)
            fail("Expected one album");

        Album retrievedAlbum = albumDao.getAlbumById(albumList.get(0).getAlbumId());
        if (retrievedAlbum == null)
            fail("Expected to retrieve album by ID");
    }

    /**
     * Test if you get the right album object from the getAlbumByTitle method.
     */
    @Test
    public void testGetAlbumByTitle() {
        albumDao.createAlbum(albumOne);

        List<Album> retrievedAlbum = albumDao.getAlbumByTitle("Album One");

        if (retrievedAlbum == null)
            fail("Expected to retrieve at least one album");
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
        albumDao.createAlbum(albumOne)
        ;
        List<Album> albumList = albumDao.getAllAlbums();
        if (albumList.size() != 1)
            fail("Expected one album");

        Album retrievedAlbum = albumDao.getAlbumById(albumList.get(0).getAlbumId());
        assertEquals(albumOne.getTitle(), retrievedAlbum.getTitle());

        albumDao.deleteAlbum(retrievedAlbum);

        List<Album> newAlbumList = albumDao.getAllAlbums();
        if (newAlbumList.size() > 0)
            fail("Expected zero albums");
    }

    /**
     * Test if the right album object is deleted when there are two albums in the database.
     */
    @Test
    public void testDeleteOneAlbumWithTwoInDatabase() {
        albumDao.createAlbum(albumOne);
        albumDao.createAlbum(albumTwo);

        List<Album> albumList = albumDao.getAllAlbums();
        if (albumList.size() != 2)
            fail("Expected two albums");

        Album retrievedAlbum = albumDao.getAlbumById(albumList.get(1).getAlbumId());
        assertEquals(albumTwo.getTitle(), retrievedAlbum.getTitle());

        albumDao.deleteAlbum(retrievedAlbum);

        List<Album> newAlbumList = albumDao.getAllAlbums();
        if (newAlbumList.size() != 1)
            fail("Expected one album");

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
        if (albumList.size() != 2)
            fail("Expected two albums");

        assertEquals(firstAlbumRetrieve.get(0).getAlbumId(), albumList.get(0).getAlbumId());
    }

}
