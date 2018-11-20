package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Filip Mik on 14. 11. 2018
 */

@ContextConfiguration(classes = ServiceConfig.class)
public class PasswordHashTest extends AbstractTestNGSpringContextTests  {

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInjection() {
        Assert.assertNotNull(beanMappingService.getMapper());
    }

    @Test
    public void testHash() {
        // Password hash should be 60 letters long
        Assert.assertEquals(60L, passwordEncoder.encode("someshittypassowrd").length());
    }
}
