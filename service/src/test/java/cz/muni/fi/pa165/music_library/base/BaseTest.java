package cz.muni.fi.pa165.music_library.base;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author Jan Ficko
 */

@ContextConfiguration(classes = ServiceConfig.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
}
