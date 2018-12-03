package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dao.interfaces.ArtistDao;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.service.ArtistService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * @author Jan Ficko
 */

@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ArtistServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private ArtistService artistService;
    @Mock
    private ArtistDao artistDao;

    private Artist artist;

    private Long artistId = 1L;
    private String artistName = "Artist";

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void init() {
        artist = new Artist();
        artist.setArtistId(artistId);
        artist.setName(artistName);
        artist.setBirthDate(new Date());
        artist.setArtistInfo("Artist Info");
    }

    @Test
    public void testGetAllArtists() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist);

        when(artistService.getAllArtists()).thenReturn(artistList);

        List<Artist> artists = artistService.getAllArtists();

        assertThat(artists).isNotNull();
        assertNotEquals(artists.size(), 0);
        assertEquals(artists, artistList);
        assertTrue(artists.contains(artist));
    }

    @Test
    public void testGetAllArtistsWhenEmpty() {
        List<Artist> artists = artistService.getAllArtists();

        assertThat(artists).isNotNull();
        assertNotEquals(artists.size(), 0);
    }

    @Test
    public void testGetArtistById() {
        when(artistService.getArtistById(artistId)).thenReturn(artist);

        assertThat(artistService.getArtistById(artistId)).isEqualTo(artist);
    }

    @Test
    public void testGetArtistByIdNull() {
        Artist artist = artistService.getArtistById(null);

        assertThat(artist).isEqualTo(null);
    }

    @Test
    public void testGetArtistByIdNonExistent() {
        when(artistService.getArtistById(2L)).thenReturn(null);

        Artist artist = artistService.getArtistById(2L);

        assertThat(artist).isNull();
    }

    @Test
    public void testGetArtistsByName() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist);

        when(artistService.getArtistsByName(artistName)).thenReturn(artistList);

        List<Artist> artists = artistService.getArtistsByName(artistName);

        assertThat(artists).isNotNull();
        assertNotEquals(artists.size(), 0);
    }

    @Test
    public void testGetArtistsByNameNull() {
        when(artistService.getArtistsByName(null)).thenReturn(new ArrayList<>());

        List<Artist> artists = artistService.getArtistsByName(null);

        assertThat(artists).isNotNull();
        assertEquals(artists.size(), 0);
    }

    @Test
    public void testGetArtistsByNameNonExistent() {
        when(artistService.getArtistsByName("Random Name")).thenReturn(new ArrayList<>());

        List<Artist> artists = artistService.getArtistsByName("Random Name");

        assertThat(artists).isNotNull();
        assertEquals(artists.size(), 0);
    }

    @Test
    public void testCreateArtist() {
        artistService.createArtist(artist);
        verify(artistDao, atLeastOnce()).createArtist(artist);
    }

    @Test
    public void testCreateArtistNull(){
        artistService.createArtist(null);
        verify(artistDao, atLeastOnce()).createArtist(null);
    }

    @Test
    public void testUpdateArtist(){
        artistService.updateArtistInfo(artist);
        verify(artistDao, atLeastOnce()).updateArtistInfo(artist);
    }

    @Test
    public void testUpdateArtistNull() {
        artistService.updateArtistInfo(null);
        verify(artistDao, atLeastOnce()).updateArtistInfo(null);
    }

    @Test
    public void testDeleteArtist() {
        artistService.deleteArtist(artist);
        verify(artistDao, atLeastOnce()).deleteArtist(artist);
    }

    @Test
    public void testDeleteArtistNull() {
        artistService.deleteArtist(null);
        verify(artistDao, atLeastOnce()).deleteArtist(null);
    }

}
