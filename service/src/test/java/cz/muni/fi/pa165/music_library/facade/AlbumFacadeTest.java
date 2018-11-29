package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dto.AlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = ServiceConfig.class)
public class AlbumFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AlbumFacade albumFacade;

    @Test
    public void createArtistTest() {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("Test");
        albumDto.setReleaseDate(new Date());
        albumDto.setCommentary("Comment");
        albumFacade.createAlbum(albumDto);

        List<AlbumDto> albumDtos = albumFacade.getAllAlbums();
        Assert.assertEquals(1,albumDtos.size());
    }
}