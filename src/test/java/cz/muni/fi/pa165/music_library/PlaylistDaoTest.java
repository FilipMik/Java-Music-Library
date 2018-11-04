package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.PlayListDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.UserDao;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Filip Mik on 3. 11. 2018
 */

@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PlaylistDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PlayListDao playListDao;

    @Autowired
    private SongDao songDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    private Playlist chillPlaylist;
    private Playlist workPlaylist;
    private Playlist travelPlaylist;

    @Before
    public void init() {
        chillPlaylist = new Playlist();
        chillPlaylist.setTitle("Chill playlist");
        chillPlaylist.setDateCreated(new Date());

        workPlaylist = new Playlist();
        workPlaylist.setTitle("Work playlist");
        workPlaylist.setDateCreated(new Date());

        travelPlaylist = new Playlist();
        travelPlaylist.setTitle("Travel playlist");
        travelPlaylist.setDateCreated(new Date());
    }

    @Test(expected = PersistenceException.class)
    public void nullNameTest() {
        Playlist playlist = new Playlist();
        playlist.setTitle(null);
        playlist.setDateCreated(new Date());
        playListDao.createPlaylist(playlist);

        Assert.assertNotEquals("There should be no data", 0L, playListDao.getAllPlaylists().size());
    }

    @Test(expected = PersistenceException.class)
    public void nullDateTest() {
        Playlist playlist = new Playlist();
        playlist.setTitle("Sample title");
        playlist.setDateCreated(null);
        playListDao.createPlaylist(playlist);

        Assert.assertNotEquals("There should be no data", 0L, playListDao.getAllPlaylists().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindUserByEmailRequiresString() {
        playListDao.createPlaylist(null);
    }

    @Test
    public void persistNewPlaylistTest() {
        playListDao.createPlaylist(chillPlaylist);
        Assert.assertEquals(1L, playListDao.getAllPlaylists().size());
    }

    @Test
    public void insertPlaylistsTest() {
        playListDao.createPlaylist(chillPlaylist);
        playListDao.createPlaylist(workPlaylist);
        playListDao.createPlaylist(travelPlaylist);

        Assert.assertEquals(3L, playListDao.getAllPlaylists().size());

        // Add already existing playlists
        playListDao.createPlaylist(chillPlaylist);
        playListDao.createPlaylist(travelPlaylist);

        Assert.assertEquals(3L, playListDao.getAllPlaylists().size());
    }

    @Test
    public void removePlaylistsTest() {
        playListDao.createPlaylist(chillPlaylist);
        playListDao.createPlaylist(workPlaylist);
        playListDao.createPlaylist(travelPlaylist);

        Assert.assertEquals(3L, playListDao.getAllPlaylists().size());

        // Delete playlists
        playListDao.deletePlaylist(chillPlaylist);
        playListDao.deletePlaylist(travelPlaylist);

        Assert.assertNull(playListDao.getPlaylistById(chillPlaylist.getPlaylistId()));
        Assert.assertNull(playListDao.getPlaylistById(travelPlaylist.getPlaylistId()));
        Assert.assertNotNull(playListDao.getPlaylistById(workPlaylist.getPlaylistId()));

        Assert.assertEquals(1L, playListDao.getAllPlaylists().size());

        playListDao.deletePlaylist(workPlaylist);

        Assert.assertEquals(0L, playListDao.getAllPlaylists().size());
    }

    @Test
    public void relationTest() {
        Song song1 = new Song();
        Song song2 = new Song();
    }
}
