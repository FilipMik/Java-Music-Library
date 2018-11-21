package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author Filip Mik on 21. 11. 2018
 */

@ContextConfiguration(classes = ServiceConfig.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {
}
