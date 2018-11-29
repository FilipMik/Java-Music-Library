package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = ServiceConfig.class)
public class ArtistFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ArtistFacade artistFacade;

    @Test
    public void createArtistTest() {
        ArtistDto artistDto = new ArtistDto();
        artistDto.setName("Peter");
        artistDto.setArtistInfo("Hitmaker");
        artistDto.setBirthDate(new Date());
        artistFacade.createArtist(artistDto);

        List<ArtistDto> artistDtoList = artistFacade.getAllArtists();
        Assert.assertEquals(1,artistDtoList.size());
    }
}
