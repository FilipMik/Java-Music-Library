package cz.muni.fi.pa165.music_library.base;

import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import org.mockito.Mock;

/**
 * @author Jan Ficko
 */

public abstract class BaseFacadeTest extends BaseTest {

    @Mock
    protected BeanMappingService beanMappingService;

}
