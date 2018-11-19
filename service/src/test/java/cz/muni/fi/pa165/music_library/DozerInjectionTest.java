package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Filip Mik on 14. 11. 2018
 */

@ContextConfiguration(classes = ServiceConfig.class)
public class DozerInjectionTest extends AbstractTestNGSpringContextTests  {

    @Autowired
    private BeanMappingService beanMappingService;

    @Test
    public void testInjection() {
        Assert.assertNotNull(beanMappingService.getMapper());
    }
}
