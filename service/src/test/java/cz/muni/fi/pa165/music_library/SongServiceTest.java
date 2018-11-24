package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.data.entities.Song;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.service.SongService;
import cz.muni.fi.pa165.music_library.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * @author Filip Mik on 24. 11. 2018
 */

@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private SongService songService;

    private Song song;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initSong() {
        song = new Song();
        song.setTitle("Amazing song numero 1");
    }


}
