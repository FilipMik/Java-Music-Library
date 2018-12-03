package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dao.interfaces.AlbumDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.SongDao;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.service.AlbumService;
import cz.muni.fi.pa165.music_library.service.AlbumServiceImpl;
import cz.muni.fi.pa165.music_library.service.TimeService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * @author Jan Ficko
 */

@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AlbumServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private AlbumService albumService;
    @Mock
    private AlbumDao albumDao;
    @Mock
    private ArtistDao artistDao;
    @Mock
    private SongDao songDao;
    @Autowired
    @Mock
    private TimeService timeService;

    private Album album;
    private Artist artist;

    private List<Album> albumList = new ArrayList<>();
    private List<Song> songList = new ArrayList<>();

    private Long albumId = 1L;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        albumService = new AlbumServiceImpl(albumDao, artistDao, songDao, timeService);
    }

    @BeforeMethod
    public void init() {
        album = new Album();
        album.setAlbumId(albumId);
        album.setTitle("Title");
        album.setReleaseDate(new Date());
        album.setCommentary("Comment");

        artist = new Artist();
        artist.setArtistId(1L);
        artist.setName("Artist");

        songList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Song song = new Song();
            song.setSongId((long) i);
            song.setTitle("Song " + i);
            songList.add(song);
        }

        album.setArtist(artist);

        albumList.add(album);
        artist.setAlbumList(albumList);
    }

    @Test
    public void testGetAllAlbums() {
        when(albumService.getAllAlbums()).thenReturn(albumList);

        List<Album> albums = albumService.getAllAlbums();

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
        assertEquals(albums, albumList);
        assertTrue(albums.contains(album));
    }

    @Test
    public void testGetAllAlbumsWhenEmpty() {
        List<Album> albums = albumService.getAllAlbums();

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
    }

    @Test
    public void testGetAlbumById() {
        when(albumService.getAlbumById(albumId)).thenReturn(album);

        assertThat(albumService.getAlbumById(albumId)).isEqualTo(album);
    }

    @Test
    public void testGetAlbumByIdNull() {
        Album album = albumService.getAlbumById(null);

        assertThat(album).isEqualTo(null);
    }

    @Test
    public void testGetAlbumByIdNonExistent() {
        when(albumService.getAlbumById(2L)).thenReturn(null);

        Album album = albumService.getAlbumById(2L);

        assertThat(album).isNull();
    }

    @Test
    public void testGetAlbumArtist() {
        when(albumDao.getAlbumById(albumId)).thenReturn(album);

        Artist artist = albumService.getAlbumArtist(albumId);

        assertThat(artist).isNotNull();
        assertEquals(artist.getName(), "Artist");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testGetAlbumArtistNonExistent() {
        when(albumDao.getAlbumById(2L)).thenReturn(null);

        Artist artist = albumService.getAlbumArtist(2L);

        assertThat(artist).isNull();
    }

    @Test
    public void testGetAlbumByTitle() {
        when(albumService.getAlbumsByTitle("Title")).thenReturn(albumList);

        List<Album> albums = albumService.getAlbumsByTitle("Title");

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
    }

    @Test
    public void testGetAlbumByTitleNull() {
        when(albumService.getAlbumsByTitle(null)).thenReturn(new ArrayList<>());

        List<Album> albums = albumService.getAlbumsByTitle(null);

        assertThat(albums).isNotNull();
        assertEquals(albums.size(), 0);
    }

    @Test
    public void testGetAlbumByTitleNonExistent() {
        when(albumService.getAlbumsByTitle("Random Word")).thenReturn(new ArrayList<>());

        List<Album> albums = albumService.getAlbumsByTitle("Random Word");

        assertThat(albums).isNotNull();
        assertEquals(albums.size(), 0);
    }

    @Test
    public void testGetAlbumByArtistName() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist);

        when(artistDao.getArtistsByName("Artist")).thenReturn(artistList);

        List<Album> albums = albumService.getAlbumsByArtistName("Artist");

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
        assertEquals(albums, albumList);
        assertTrue(albums.contains(album));
    }

    @Test
    public void testGetAlbumByArtistNameNonExsistent() {
        when(artistDao.getArtistsByName("Random Artist")).thenReturn(new ArrayList<>());

        List<Album> albums = albumService.getAlbumsByArtistName("Random Artist");

        assertThat(albums).isNotNull();
        assertEquals(albums.size(), 0);
    }

    @Test
    public void testGetAlbumByArtistNameNull() {
        when(albumService.getAlbumsByArtistName(null)).thenReturn(new ArrayList<>());

        List<Album> albums = albumService.getAlbumsByArtistName(null);

        assertThat(albums).isNotNull();
        assertEquals(albums.size(), 0);
    }

    @Test
    public void testGetLastWeeksAlbums() {
        List<Album> albums = new ArrayList<>();

        Album currentAlbum = new Album();
        currentAlbum.setAlbumId(1L);
        currentAlbum.setReleaseDate(new Date());
        Album currentAlbum2 = new Album();
        currentAlbum2.setAlbumId(2L);
        currentAlbum2.setReleaseDate(new Date(System.currentTimeMillis() - 304800001));
        Album currentAlbum3 = new Album();
        currentAlbum2.setAlbumId(3L);
        currentAlbum2.setReleaseDate(new Date());
        albums.add(currentAlbum);
        albums.add(currentAlbum2);
        albums.add(currentAlbum3);

        when(albumDao.getAllAlbumsBetween(any(Date.class), any(Date.class))).thenReturn(albums);
        when(timeService.getCurrentDate()).thenReturn(new Date());

        List<Album> lastWeekAlbums = albumService.getLastWeekAlbums();

        assertThat(lastWeekAlbums).isNotNull();
        assertEquals(lastWeekAlbums.size(), 3);
        assertEquals(lastWeekAlbums, albums);
        assertTrue(lastWeekAlbums.contains(currentAlbum));
    }

    @Test
    public void testCreateAlbum() {
        albumService.createAlbum(album);
        verify(albumDao, atLeastOnce()).createAlbum(album);
    }

    @Test
    public void testCreateAlbumNull(){
        albumService.createAlbum(null);
        verify(albumDao, atLeastOnce()).createAlbum(null);
    }

    @Test
    public void testUpdateAlbum(){
        albumService.updateAlbum(album);
        verify(albumDao, atLeastOnce()).updateAlbum(album);
    }

    @Test
    public void testUpdateAlbumNull() {
        albumService.updateAlbum(null);
        verify(albumDao, atLeastOnce()).updateAlbum(null);
    }

    @Test
    public void testAddSongs() {
        when(albumDao.getAlbumById(albumId)).thenReturn(album);

        List<Long> songIdList = new ArrayList<>();
        for (Song song : songList) {
            songIdList.add(song.getSongId());
            when(songDao.getSongById(song.getSongId())).thenReturn(song);
        }
        albumService.addSongs(albumId, songIdList);

        Album foundAlbum = albumService.getAlbumById(albumId);

        assertThat(foundAlbum).isNotNull();
        assertEquals(foundAlbum.getSongList().size(), 5);
    }

    @Test
    public void testAddSong() {
        when(albumDao.getAlbumById(albumId)).thenReturn(album);
        when(songDao.getSongById(songList.get(0).getSongId())).thenReturn(songList.get(0));

        albumService.addSong(albumId, songList.get(0).getSongId());

        Album foundAlbum = albumService.getAlbumById(albumId);

        assertThat(foundAlbum).isNotNull();
        assertNotEquals(foundAlbum.getSongList().size(), 0);
    }

    @Test
    public void testRemoveSong() {
        when(albumDao.getAlbumById(albumId)).thenReturn(album);
        when(songDao.getSongById(songList.get(4).getSongId())).thenReturn(songList.get(4));

        albumService.addSong(albumId, songList.get(4).getSongId());
        Album foundAlbum = albumService.getAlbumById(albumId);

        assertThat(foundAlbum).isNotNull();
        assertEquals(foundAlbum.getSongList().size(), 1);

        albumService.removeSong(albumId, songList.get(4).getSongId());
        Album foundAlbumRemovedSong = albumService.getAlbumById(albumId);

        assertThat(foundAlbumRemovedSong).isNotNull();
        assertEquals(foundAlbumRemovedSong.getSongList().size(), 0);
    }

    @Test
    public void testDeleteAlbum() {
        albumService.deleteAlbum(album);
        verify(albumDao, atLeastOnce()).deleteAlbum(album);
    }

    @Test
    public void testDeleteAlbumNull() {
        albumService.deleteAlbum(null);
        verify(albumDao, atLeastOnce()).deleteAlbum(null);
    }

}
