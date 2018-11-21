package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExsistsException;
import cz.muni.fi.pa165.music_library.exceptions.IncorrectPasswordException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExsistsException;
import cz.muni.fi.pa165.music_library.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Filip Mik on 21. 11. 2018
 */

@ContextConfiguration(classes = ServiceConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initUser() {
        user = new User();
        user.setUsername("Felipe");
        user.setEmail("felipe@mike.cz");
        user.setUserLevel(UserLevel.BasicUser);
    }

    @Test
    private void registerUserTest() {
        try {
            userService.registerUser(user, "heslo123");
        } catch (EmailAlreadyExsistsException | UsernameAlreadyExsistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    private void loginUserTest() {
        try {
            userService.registerUser(user, "heslo123");
        } catch (EmailAlreadyExsistsException | UsernameAlreadyExsistsException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertTrue(userService.loginUser(user, "heslo123"));
        } catch (IncorrectPasswordException e) {
            e.printStackTrace();
        }
    }
}
