package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dto.PlaylistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = ServiceConfig.class)
public class PlaylistFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PlaylistFacade playlistFacade;

    @Test
    public void createArtistTest() {
        PlaylistDto playlistDto = new PlaylistDto();
        playlistDto.setTitle("Critical");
        playlistDto.setDateCreated(new Date());
        playlistFacade.createPlaylist(playlistDto);

        List<PlaylistDto> playlistDtos = playlistFacade.getAllPlaylists();
        Assert.assertEquals(1,playlistDtos.size());
    }
}