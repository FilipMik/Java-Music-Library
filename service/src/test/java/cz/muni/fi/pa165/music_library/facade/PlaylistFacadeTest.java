package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.base.BaseFacadeTest;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.dto.PlaylistDto;
import cz.muni.fi.pa165.music_library.service.PlaylistService;
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

public class PlaylistFacadeTest extends BaseFacadeTest {

    @Autowired
    @InjectMocks
    private PlaylistFacade playlistFacade;

    @Mock
    private PlaylistService playlistService;

    private Playlist playlist;
    private List<Playlist> playlistList = new ArrayList<>();

    private PlaylistDto playlistDto;
    private List<PlaylistDto> playlistDtoList = new ArrayList<>();

    private Long playlistId = 1L;
    private String playlistTitle = "Title";

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(playlistFacade, "playlistService", playlistService);
        ReflectionTestUtils.setField(playlistFacade, "beanMappingService", beanMappingService);

        Date date = new Date();

        playlist = new Playlist();
        playlist.setPlaylistId(playlistId);
        playlist.setTitle(playlistTitle);
        playlist.setDateCreated(date);

        playlistDto = new PlaylistDto();
        playlistDto.setPlaylistId(playlistId);
        playlistDto.setTitle(playlistTitle);
        playlistDto.setDateCreated(date);

        playlistList.add(playlist);
        playlistDtoList.add(playlistDto);
    }

    @Test
    public void getAllPlaylistsTest() {
        when(playlistService.getAllPlaylists()).thenReturn(playlistList);
        when(beanMappingService.mapTo(playlistList, PlaylistDto.class)).thenReturn(playlistDtoList);

        List<PlaylistDto> playlists = playlistFacade.getAllPlaylists();

        assertThat(playlists).isNotNull();
        assertThat(playlist.getTitle()).isEqualTo(playlists.get(0).getTitle());
        verify(playlistService).getAllPlaylists();
        assertNotEquals(playlists.size(), 0);
        assertTrue(playlists.contains(playlistDto));
    }

    @Test
    public void findPlaylistByIdTest() {
        when(beanMappingService.mapTo(playlist, PlaylistDto.class)).thenReturn(playlistDto);
        when(playlistService.getPlaylistById(playlistId)).thenReturn(playlist);

        PlaylistDto playlistDto = playlistFacade.findPlaylistById(playlistId);

        assertThat(playlistDto).isNotNull();
        assertThat(playlistDto.getTitle()).isEqualTo(playlist.getTitle());
        verify(playlistService).getPlaylistById(playlistId);
        verify(beanMappingService).mapTo(playlist, PlaylistDto.class);
    }

    @Test
    public void findPlaylistsByTitleTest() {
        when(playlistService.getPlaylistsByTitle(playlistTitle)).thenReturn(playlistList);
        when(beanMappingService.mapTo(playlistList, PlaylistDto.class)).thenReturn(playlistDtoList);

        List<PlaylistDto> playlists = playlistFacade.findPlaylistsByTitle(playlistTitle);

        assertThat(playlists).isNotNull();
        assertNotEquals(playlists.size(), 0);
        assertThat(playlist.getTitle()).isEqualTo(playlists.get(0).getTitle());
        assertTrue(playlists.contains(playlistDto));
        verify(playlistService).getPlaylistsByTitle(playlistTitle);
    }

    @Test
    public void createPlaylistTest() {
        when(beanMappingService.mapTo(playlistDto, Playlist.class)).thenReturn(playlist);

        playlistFacade.createPlaylist(playlistDto);

        verify(playlistService).createPlaylist(playlist);
        verify(beanMappingService).mapTo(playlistDto, Playlist.class);
    }

    @Test
    public void updatePlaylistTest() {
        PlaylistDto playlistUpdatedDto = playlistDto;
        playlistUpdatedDto.setTitle("Updated");
        Playlist playlistUpdated = playlist;
        playlistUpdated.setTitle("Updated");

        when(beanMappingService.mapTo(playlistDto, Playlist.class)).thenReturn(playlistUpdated);

        playlistFacade.updatePlaylist(playlistUpdatedDto);

        verify(playlistService).updatePlaylist(playlistUpdated);
        verify(beanMappingService).mapTo(playlistDto, Playlist.class);
    }

    @Test
    public void addSongsTest() {
        List<Long> songIdList = new ArrayList<>();
        songIdList.add(1L);
        songIdList.add(2L);

        playlistFacade.addSongs(playlistId, songIdList);

        verify(playlistService).addSongs(playlistId, songIdList);
    }

    @Test
    public void addSongTest() {
        playlistFacade.addSong(playlistId, 1L);

        verify(playlistService).addSong(playlistId, 1L);
    }



    @Test
    public void removeSongTest() {
        playlistFacade.removeSong(playlistId, 1L);

        verify(playlistService).removeSong(playlistId, 1L);
    }

    @Test
    public void deletePlaylistTest() {
        when(beanMappingService.mapTo(playlistDto, Playlist.class)).thenReturn(playlist);

        playlistFacade.deletePlaylist(playlistDto);

        verify(playlistService).deletePlaylist(playlist);
    }

}
