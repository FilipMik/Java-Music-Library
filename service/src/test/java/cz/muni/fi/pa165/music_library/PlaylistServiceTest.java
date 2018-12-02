package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dao.interfaces.PlayListDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.service.PlaylistService;
import cz.muni.fi.pa165.music_library.service.PlaylistServiceImpl;
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

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for Playlist service implementation.
 * @author Klara Minsterova
 */

@ContextConfiguration(classes = ServiceConfig.class)
public class PlaylistServiceTest  extends AbstractTestNGSpringContextTests {

    @Mock
    private PlayListDao playlistDao;

    @Mock
    private SongDao songDao;

    @Autowired
    @InjectMocks
    private PlaylistService playlistService;

    private Playlist playlist1;
    private Playlist playlist2;

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        playlistService = new PlaylistServiceImpl(playlistDao, songDao);
    }

    @BeforeMethod
    public void initSong() {
        playlist1 = new Playlist();
        playlist1.setPlaylistId(1l);

        playlist2 = new Playlist();
    }

    @Test
    public void testCreatePlaylist() {
        playlistService.createPlaylist(playlist1);
        verify(playlistDao).createPlaylist(playlist1);
    }

    @Test
    public void testGetAllPlaylists() {
        List<Playlist> allPlaylists = Arrays.asList(playlist1, playlist2);
        when(playlistDao.getAllPlaylists()).thenReturn(allPlaylists);
        List<Playlist> result = playlistService.getAllPlaylists();

        verify(playlistDao).getAllPlaylists();
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), playlist1);
    }

    @Test
    public void testGetPlaylistsByTitle() {
        List<Playlist> allPlaylists = Arrays.asList(playlist1, playlist2);
        when(playlistDao.getPlaylistsByTitle(anyString())).thenReturn(allPlaylists);
        List<Playlist> result = playlistService.getPlaylistsByTitle("title");

        verify(playlistDao).getPlaylistsByTitle("title");
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), playlist1);
    }

    @Test
    public void testGetPlaylistById() {
        when(playlistDao.getPlaylistById(playlist1.getPlaylistId())).thenReturn(playlist1);
        Playlist result = playlistService.getPlaylistById(1l);

        verify(playlistDao).getPlaylistById(1l);
        Assert.assertEquals(result, playlist1);
    }

    @Test
    public void testDeletePlaylist() {
        playlistService.deletePlaylist(playlist1);
        verify(playlistDao).deletePlaylist(playlist1);
    }

    @Test
    public void testUpdatePlaylist() {
        playlistService.updatePlaylist(playlist1);
        verify(playlistDao).updatePlaylist(playlist1);
    }
}
