package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Žiška
 * ID: 487569
 * Class defines test cases for ArtistDao implementation
 */
@ContextConfiguration(classes=ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ArtistDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private SongDao songDao;

    private Artist eminemArtist;
    private Artist passengerArtist;
    private Artist dynoroArtist;

    @Before
    public void init() {
        eminemArtist = new Artist();
        eminemArtist.setName("Eminem");
        eminemArtist.setBirthDate(new Date());
        eminemArtist.setArtistInfo("Rapper");

        passengerArtist = new Artist();
        passengerArtist.setName("Passenger");
        passengerArtist.setBirthDate(new Date());
        passengerArtist.setArtistInfo("Folk rock");

        dynoroArtist = new Artist();
        dynoroArtist.setName("Dynoro");
        dynoroArtist.setBirthDate(new Date());
        dynoroArtist.setArtistInfo("Hit maker");
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullArtistNameNotAllowedTest() {
        Artist artist = new Artist();
        artist.setName(null);
        artistDao.createArtist(artist);
    }

    /**
     * Checks that find by name works
     * Test cases:
     *      1.find by name "Dynoro"
     *      2.persist new entity with same name, expected size of returned array should be 2
     *      3.changes new entity name to different one, expected size of returned array should be 1
     */
    @Test()
    public void findByNameTest() {
        artistDao.createArtist(eminemArtist);
        artistDao.createArtist(passengerArtist);
        artistDao.createArtist(dynoroArtist);

        List<Artist> dynoroFound = artistDao.getArtistByName("Dynoro");

        Assert.assertEquals(1L,dynoroFound.size());
        Assert.assertEquals(dynoroArtist,dynoroFound.get(0));
        Assert.assertTrue(dynoroFound.contains(dynoroArtist));

        Artist dynoro2 = new Artist();
        dynoro2.setName("Dynoro");
        dynoro2.setBirthDate(new Date());
        dynoro2.setArtistInfo("New Dynoro");

        artistDao.createArtist(dynoro2);
        dynoroFound = artistDao.getArtistByName("Dynoro");

        Assert.assertEquals(2L,dynoroFound.size());
        Assert.assertNotEquals(dynoro2,dynoroArtist);

        dynoro2.setName("new Dynoro");
        artistDao.updateArtistInfo(dynoro2);
        dynoroFound = artistDao.getArtistByName("Dynoro");

        Assert.assertEquals(1L,dynoroFound.size());

    }

    /**
     * Checks that three entities are stored into database
     *
     */
    @Test()
    public void insertArtistsTest() {
        artistDao.createArtist(eminemArtist);
        artistDao.createArtist(passengerArtist);
        artistDao.createArtist(dynoroArtist);

        List<Artist> artistsFound = artistDao.getAllArtists();

        Assert.assertEquals("After insertion: Three entities should be persisted",3L, artistsFound.size());
        Assert.assertTrue("Database should contain Passenger", artistDao.getAllArtists().contains(passengerArtist));
        Assert.assertTrue("Database should contain Dynoro",artistDao.getAllArtists().contains(dynoroArtist));
        Assert.assertTrue("Database should contain Eminem",artistDao.getAllArtists().contains(eminemArtist));
    }

    /**
     * Checks that delete function works
     *
     */
    @Test()
    public void deleteArtistTest() {
        artistDao.createArtist(eminemArtist);
        artistDao.createArtist(passengerArtist);
        artistDao.createArtist(dynoroArtist);

        artistDao.deleteArtist(eminemArtist);

        Artist foundArtist = artistDao.getArtistById(eminemArtist.getArtistId());
        Assert.assertNull("Eminem is not persisted enymore: should be NULL", foundArtist);
        Assert.assertEquals("After delete operation: Number of column should change - expected 2",
                2L,
                artistDao.getAllArtists().size());

        Assert.assertTrue("Database should contain Passenger", artistDao.getAllArtists().contains(passengerArtist));
        Assert.assertTrue("Database should contain Dynoro",artistDao.getAllArtists().contains(dynoroArtist));
        Assert.assertFalse("Database should not contain Eminem",artistDao.getAllArtists().contains(eminemArtist));

    }

    /**
     * Checks that created entity is changed after update call
     *
     */
    @Test()
    public void updateTest() {
        artistDao.createArtist(eminemArtist);
        artistDao.createArtist(passengerArtist);
        artistDao.createArtist(dynoroArtist);

        Assert.assertEquals("",
                dynoroArtist.getName(),
                artistDao.getArtistById(dynoroArtist.getArtistId()).getName());

        dynoroArtist.setName("New Dynoro");
        artistDao.updateArtistInfo(dynoroArtist);

        Assert.assertEquals("After update, number of rows in table shouldn't change",
                3L,
                artistDao.getAllArtists().size());
        Assert.assertEquals("Updated entity should be changed",
                "New Dynoro",
                artistDao.getArtistById(dynoroArtist.getArtistId()).getName());

    }


    @Test()
    public void relationSongAndArtistTest() {
        Song firstSong = new Song();
        firstSong.setTitle("First Song");
        Song secondSong = new Song();
        secondSong.setTitle("Second Song");

        eminemArtist.addSong(firstSong);
        eminemArtist.addSong(secondSong);
        firstSong.setArtist(eminemArtist);
        secondSong.setArtist(eminemArtist);

        songDao.createSong(firstSong);
        songDao.createSong(secondSong);
        artistDao.createArtist(eminemArtist);

        Artist foundArtist = artistDao.getArtistById(eminemArtist.getArtistId());
        Assert.assertEquals("Song list should contain 2 songs in Artist",2L, foundArtist.getSongList().size());
        Song firstSongFound = foundArtist.getSongList().get(0);
        Song secondSongFound = foundArtist.getSongList().get(1);
        Assert.assertEquals("Expected First song", firstSong, firstSongFound);
        Assert.assertEquals("Expected Second song", secondSong, secondSongFound);

        Song songFound = songDao.getSongById(firstSong.getSongId());
        Assert.assertEquals("Song should belong to Eminem","Eminem", songFound.getArtist().getName());
    }
}
