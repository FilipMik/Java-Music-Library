package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.base.BaseFacadeTest;
import cz.muni.fi.pa165.music_library.data.entities.Album;
import cz.muni.fi.pa165.music_library.data.entities.Artist;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import cz.muni.fi.pa165.music_library.dto.SongDto;
import cz.muni.fi.pa165.music_library.service.AlbumService;
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

public class AlbumFacadeTest extends BaseFacadeTest {

    @Autowired
    @InjectMocks
    private AlbumFacade albumFacade;

    @Mock
    private AlbumService albumService;

    private Album album;
    private Artist artist;
    private List<Song> songList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();

    private AlbumDto albumDto;
    private ArtistDto artistDto;
    private List<SongDto> songDtoList = new ArrayList<>();
    private List<AlbumDto> albumDtoList = new ArrayList<>();

    private Long albumId = 1L;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(albumFacade, "albumService", albumService);
        ReflectionTestUtils.setField(albumFacade, "beanMappingService", beanMappingService);

        Date date = new Date();

        album = new Album();
        album.setAlbumId(albumId);
        album.setTitle("Title");
        album.setReleaseDate(date);
        album.setCommentary("Comment");
        artist = new Artist();
        artist.setName("Artist");
        album.setArtist(artist);
        Song songOne = new Song();
        songOne.setTitle("Song One");
        Song songTwo = new Song();
        songTwo.setTitle("Song Two");
        songList.add(songOne);
        songList.add(songTwo);
        album.setSongList(songList);

        albumDto = new AlbumDto();
        albumDto.setAlbumId(albumId);
        albumDto.setTitle("Title");
        albumDto.setReleaseDate(date);
        albumDto.setCommentary("Comment");
        artistDto = new ArtistDto();
        artistDto.setName("Artist");
        albumDto.setArtist(artistDto);
        SongDto songDtoOne = new SongDto();
        songDtoOne.setTitle("Song One");
        SongDto songDtoTwo = new SongDto();
        songDtoTwo.setTitle("Song Two");
        songDtoList.add(songDtoOne);
        songDtoList.add(songDtoTwo);
        albumDto.setSongList(songDtoList);

        albumList.add(album);
        albumDtoList.add(albumDto);
    }

    @Test
    public void getAllAlbumsTest() {
        when(albumService.getAllAlbums()).thenReturn(albumList);
        when(beanMappingService.mapTo(albumList, AlbumDto.class)).thenReturn(albumDtoList);

        List<AlbumDto> albums = albumFacade.getAllAlbums();

        assertThat(album.getTitle()).isEqualTo(albums.get(0).getTitle());
        verify(albumService).getAllAlbums();
        assertNotEquals(albums.size(), 0);
        assertTrue(albums.contains(albumDto));
    }

    @Test
    public void findAlbumByIdTest() {
        when(beanMappingService.mapTo(album, AlbumDto.class)).thenReturn(albumDto);
        when(albumService.getAlbumById(albumId)).thenReturn(album);

        AlbumDto albumDto = albumFacade.findAlbumById(albumId);

        assertThat(albumDto).isNotNull();
        assertThat(albumDto.getTitle()).isEqualTo(album.getTitle());
        verify(albumService).getAlbumById(albumId);
        verify(beanMappingService).mapTo(album, AlbumDto.class);
    }

    @Test
    public void findAlbumArtistTest() {
        when(beanMappingService.mapTo(album, AlbumDto.class)).thenReturn(albumDto);
        when(beanMappingService.mapTo(artist, ArtistDto.class)).thenReturn(artistDto);
        when(albumService.getAlbumArtist(albumId)).thenReturn(artist);

        ArtistDto artistDto = albumFacade.findAlbumArtist(albumId);

        assertThat(artistDto).isNotNull();
        assertThat(artistDto.getName()).isEqualTo(artist.getName());
        verify(albumService).getAlbumsByArtist(albumId);
        verify(beanMappingService).mapTo(artist, ArtistDto.class);
    }

    @Test
    public void findAlbumsByTitleTest() {
        when(albumService.getAlbumsByTitle("Title")).thenReturn(albumList);
        when(beanMappingService.mapTo(albumList, AlbumDto.class)).thenReturn(albumDtoList);

        List<AlbumDto> albums = albumFacade.findAlbumsByTitle("Title");

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
        assertThat(album.getTitle()).isEqualTo(albums.get(0).getTitle());
        assertTrue(albums.contains(albumDto));
        verify(albumService).getAlbumsByTitle("Title");
    }

    @Test
    public void findAlbumsByArtistTest() {
        when(albumService.getAlbumsByArtistName("Artist")).thenReturn(albumList);
        when(beanMappingService.mapTo(albumList, AlbumDto.class)).thenReturn(albumDtoList);

        List<AlbumDto> albums = albumFacade.findAlbumsByArtist("Artist");

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
        assertThat(album.getArtist().getName()).isEqualTo(albums.get(0).getArtist().getName());
        verify(albumService).getAlbumsByArtistName("Artist");
    }

    @Test
    public void findAlbumsByArtistIdTest() {
        when(albumService.getAlbumsByArtist(1L)).thenReturn(albumList);
        when(beanMappingService.mapTo(albumList, AlbumDto.class)).thenReturn(albumDtoList);

        List<AlbumDto> albums = albumFacade.findAlbumsByArtistId(1L);

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
        assertThat(album.getArtist().getArtistId()).isEqualTo(albums.get(0).getArtist().getArtistId());
        verify(albumService).getAlbumsByArtist(1L);
    }

    @Test
    public void getLastWeekAlbums() {
        when(albumService.getLastWeekAlbums()).thenReturn(albumList);
        when(beanMappingService.mapTo(albumList, AlbumDto.class)).thenReturn(albumDtoList);

        List<AlbumDto> albums = albumFacade.getLastWeekAlbums();

        assertThat(albums).isNotNull();
        assertNotEquals(albums.size(), 0);
        verify(albumService).getLastWeekAlbums();
    }

    @Test
    public void createAlbumTest() {
        when(beanMappingService.mapTo(albumDto, Album.class)).thenReturn(album);

        albumFacade.createAlbum(albumDto);

        verify(albumService).createAlbum(album);
        verify(beanMappingService).mapTo(albumDto, Album.class);
    }

    @Test
    public void updateAlbumTest() {
        AlbumDto albumUpdatedDto = albumDto;
        albumUpdatedDto.setTitle("Updated");
        Album albumUpdated = album;
        albumUpdated.setTitle("Updated");

        when(beanMappingService.mapTo(albumDto, Album.class)).thenReturn(albumUpdated);

        albumFacade.updateAlbum(albumUpdatedDto);

        verify(albumService).updateAlbum(albumUpdated);
        verify(beanMappingService).mapTo(albumDto, Album.class);
    }

    @Test
    public void addSongsTest() {
        List<Long> songIdList = new ArrayList<>();
        songIdList.add(1L);
        songIdList.add(2L);

        albumFacade.addSongs(albumId, songIdList);

        verify(albumService).addSongs(albumId, songIdList);
    }

    @Test
    public void addSongTest() {
        albumFacade.addSong(albumId, 1L);

        verify(albumService).addSong(albumId, 1L);
    }

    @Test
    public void removeSongTest() {
        albumFacade.removeSong(albumId, 1L);

        verify(albumService).removeSong(albumId, 1L);
    }

    @Test
    public void deleteAlbumTest() {
        when(beanMappingService.mapTo(albumDto, Album.class)).thenReturn(album);

        albumFacade.deleteAlbum(albumDto);

        verify(albumService).deleteAlbum(album);
    }

}
