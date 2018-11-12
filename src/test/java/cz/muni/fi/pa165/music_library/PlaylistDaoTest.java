package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.dao.interfaces.*;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.data.entities.User;
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
import java.util.List;

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

    @Test(expected = IllegalArgumentException.class)
    public void nullNameTest() {
        Playlist playlist = new Playlist();
        playlist.setTitle(null);
        playlist.setDateCreated(new Date());
        playListDao.createPlaylist(playlist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDateTest() {
        Playlist playlist = new Playlist();
        playlist.setTitle("Sample title");
        playlist.setDateCreated(null);
        playListDao.createPlaylist(playlist);
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
    public void updateTest() {
        playListDao.createPlaylist(workPlaylist);

        Assert.assertEquals("Work playlist",
                playListDao.getPlaylistById(workPlaylist.getPlaylistId()).getTitle());

        workPlaylist.setTitle("New work playlist");
        playListDao.updatePlaylist(workPlaylist);

        Assert.assertNotEquals("Work playlist",
                playListDao.getPlaylistById(workPlaylist.getPlaylistId()).getTitle());
        Assert.assertEquals("New work playlist",
                playListDao.getPlaylistById(workPlaylist.getPlaylistId()).getTitle());
    }

    @Test
    public void testSongRelations() {
        playListDao.createPlaylist(workPlaylist);

        Song song1 = new Song();
        Song song2 = new Song();

        song1.setTitle("Song1");
        song1.setCommentary("Song1 commentary");
        song1.addPlaylist(playListDao.getPlaylistById(workPlaylist.getPlaylistId()));

        song2.setTitle("Song2");
        song2.setCommentary("Song2 commentary");
        song2.addPlaylist(playListDao.getPlaylistById(workPlaylist.getPlaylistId()));

        songDao.createSong(song1);
        songDao.createSong(song2);

        workPlaylist.addSong(songDao.getSongById(song1.getSongId()));
        workPlaylist.addSong(songDao.getSongById(song2.getSongId()));

        List<Song> foundSongList = playListDao.getPlaylistById(workPlaylist.getPlaylistId()).getSongList();

        Assert.assertEquals(2L, foundSongList.size());
        Assert.assertEquals("Song1", foundSongList.get(0).getTitle());
        Assert.assertEquals("Song2 commentary", foundSongList.get(foundSongList.size() - 1).getCommentary());

        Assert.assertEquals("Work playlist",
                songDao.getSongById(song1.getSongId()).getPlaylists().get(0).getTitle());

        workPlaylist.removeSong(songDao.getSongById(song1.getSongId()));
        workPlaylist.removeSong(songDao.getSongById(song2.getSongId()));

        Assert.assertEquals(0L, playListDao.getPlaylistById(workPlaylist.getPlaylistId()).getSongList().size());
    }


    @Test
    public void testUserRelations() {
        playListDao.createPlaylist(workPlaylist);

        User user1 = new User();

        user1.setEmail("sample@email.cz");
        user1.setUsername("user1");
        user1.addPlaylist(playListDao.getPlaylistById(workPlaylist.getPlaylistId()));

        userDao.createUser(user1);

        Assert.assertEquals(1L, userDao.getUserById(user1.getUserId()).getPlaylists().size());
        workPlaylist.setUser(userDao.getUserById(user1.getUserId()));

        Assert.assertEquals("user1",
                playListDao.getPlaylistById(workPlaylist.getPlaylistId()).getUser().getUsername());

        userDao.getUserById(user1.getUserId()).removePlaylist(playListDao.getPlaylistById(workPlaylist.getPlaylistId()));

        Assert.assertEquals(0L, userDao.getUserById(user1.getUserId()).getPlaylists().size());
    }
}
