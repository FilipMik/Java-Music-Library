package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.base.BaseFacadeTest;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.service.ArtistService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class ArtistFacadeTest extends BaseFacadeTest {

    @Autowired
    @InjectMocks
    private ArtistFacade artistFacade;

    @Mock
    private ArtistService artistService;

    private Artist artist;
    private List<Artist> artistList = new ArrayList<>();

    private ArtistDto artistDto;
    private List<ArtistDto> artistDtoList = new ArrayList<>();

    private Long artistId = 1L;
    private String artistName = "Name";

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(artistFacade, "artistService", artistService);
        ReflectionTestUtils.setField(artistFacade, "beanMappingService", beanMappingService);

        Date date = new Date();

        artist = new Artist();
        artist.setArtistId(artistId);
        artist.setName(artistName);
        artist.setBirthDate(date);
        artist.setArtistInfo("Info");

        artistDto = new ArtistDto();
        artistDto.setArtistId(artistId);
        artistDto.setName(artistName);
        artistDto.setBirthDate(date);
        artistDto.setArtistInfo("Info");

        artistList.add(artist);
        artistDtoList.add(artistDto);
    }

    @Test
    public void getAllArtistsTest() {
        when(artistService.getAllArtists()).thenReturn(artistList);
        when(beanMappingService.mapTo(artistList, ArtistDto.class)).thenReturn(artistDtoList);

        List<ArtistDto> artists = artistFacade.getAllArtists();

        assertThat(artists).isNotNull();
        assertThat(artist.getName()).isEqualTo(artists.get(0).getName());
        verify(artistService).getAllArtists();
        assertNotEquals(artists.size(), 0);
        assertTrue(artists.contains(artistDto));
    }

    @Test
    public void findArtistById() {
        when(beanMappingService.mapTo(artist, ArtistDto.class)).thenReturn(artistDto);
        when(artistService.getArtistById(artistId)).thenReturn(artist);

        ArtistDto artistDto = artistFacade.findArtistById(artistId);

        assertThat(artistDto).isNotNull();
        assertThat(artistDto.getArtistId()).isEqualTo(artist.getArtistId());
        verify(artistService).getArtistById(artistId);
        verify(beanMappingService).mapTo(artist, ArtistDto.class);
    }

    @Test
    public void findArtistsByNameTest() {
        when(artistService.getArtistsByName(artistName)).thenReturn(artistList);
        when(beanMappingService.mapTo(artistList, ArtistDto.class)).thenReturn(artistDtoList);

        List<ArtistDto> artists = artistFacade.findArtistsByName(artistName);

        assertThat(artists).isNotNull();
        assertNotEquals(artists.size(), 0);
        assertThat(artist.getName()).isEqualTo(artists.get(0).getName());
        assertTrue(artists.contains(artistDto));
        verify(artistService).getArtistsByName(artistName);
    }

    @Test
    public void createArtistTest() {
        when(beanMappingService.mapTo(artistDto, Artist.class)).thenReturn(artist);

        artistFacade.createArtist(artistDto);

        verify(artistService).createArtist(artist);
        verify(beanMappingService).mapTo(artistDto, Artist.class);
    }

    @Test
    public void updateArtistTest() {
        ArtistDto artistUpdatedDto = artistDto;
        artistUpdatedDto.setName("Updated");
        Artist artistUpdated = artist;
        artistUpdated.setName("Updated");

        when(beanMappingService.mapTo(artistDto, Artist.class)).thenReturn(artistUpdated);

        artistFacade.updateArtist(artistUpdatedDto);

        verify(artistService).updateArtistInfo(artistUpdated);
        verify(beanMappingService).mapTo(artistDto, Artist.class);
    }

    @Test
    public void deleteArtistTest() {
        when(beanMappingService.mapTo(artistDto, Artist.class)).thenReturn(artist);

        artistFacade.deleteArtist(artistDto);

        verify(artistService).deleteArtist(artist);
    }

}
