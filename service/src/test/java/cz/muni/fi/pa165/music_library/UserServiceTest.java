package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.config.ServiceConfig;
import cz.muni.fi.pa165.music_library.dao.interfaces.UserDao;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.IncorrectPasswordException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import cz.muni.fi.pa165.music_library.service.UserService;
import cz.muni.fi.pa165.music_library.service.UserServiceImpl;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for User service implementation.
 * @author Klara Minsterova
 */

@ContextConfiguration(classes = ServiceConfig.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeMethod
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDao, passwordEncoder);
    }

    @BeforeMethod
    public void initUser() {
        user1 = new User();
        user1.setUserId(1l);
        user1.setUsername("Felipe");
        user1.setEmail("felipe@mike.cz");
        user1.setUserLevel(UserLevel.BasicUser);
        user1.setPassword(passwordEncoder.encode("password123"));
        user2 = new User();
    }

    @Test
    public void testGetUserById() {
        when(userDao.getUserById(user1.getUserId())).thenReturn(user1);
        User result = userService.findUserById(1l);

        verify(userDao).getUserById(1l);
        Assert.assertEquals(result, user1);
    }

    @Test
    public void testGetUserByUsername() {
        when(userDao.getUserByName(user1.getUsername())).thenReturn(user1);
        User result = userService.findUserByUsername("Felipe");

        verify(userDao).getUserByName("Felipe");
        Assert.assertEquals(result, user1);
    }

    @Test
    public void testGetUserByEmail() {
        when(userDao.getUserByEmail(user1.getEmail())).thenReturn(user1);
        User result = userService.findUserByEmail("felipe@mike.cz");

        verify(userDao).getUserByEmail("felipe@mike.cz");
        Assert.assertEquals(result, user1);
    }

    @Test
    public void testGetAllUsers() {
        List<User> allUsers = Arrays.asList(user1, user2);
        when(userDao.getAllUsers()).thenReturn(allUsers);
        List<User> result = userService.getAllUsers();

        verify(userDao).getAllUsers();
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), user1);
    }

    @Test
    public void testIsUserAdmin() {
        boolean admin = userService.isUserAdmin(user1);

        Assert.assertEquals(false, admin);
    }

    @Test
    public void testRegisterUser() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        when(userDao.getUserByName(user1.getUsername())).thenReturn(null);
        when(userDao.getUserByEmail(user1.getEmail())).thenReturn(null);
        userService.registerUser(user1, "password");

        verify(userDao).createUser(user1);
    }

    @Test (expectedExceptions = EmailAlreadyExistsException.class)
    public void testRegisterUserEmailExist() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        when(userDao.getUserByName(user1.getUsername())).thenReturn(null);
        when(userDao.getUserByEmail(user1.getEmail())).thenReturn(user1);
        userService.registerUser(user1, "password");
    }

    @Test (expectedExceptions = UsernameAlreadyExistsException.class)
    public void testRegisterUserUsernameExist() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        when(userDao.getUserByName(user1.getUsername())).thenReturn(user1);
        when(userDao.getUserByEmail(user1.getEmail())).thenReturn(null);
        userService.registerUser(user1, "password");
    }

    @Test
    public void testLoginUser() throws IncorrectPasswordException {
        when(userDao.getUserById(user1.getUserId())).thenReturn(user1);
        boolean correct = userService.loginUser(user1, "wrong password");

        Assert.assertEquals(false, correct);
    }

    @Test
    public void testLoginUserWrongPassword() throws IncorrectPasswordException {
        when(userDao.getUserById(user1.getUserId())).thenReturn(user1);
        boolean correct = userService.loginUser(user1, "password123");

        Assert.assertEquals(true, correct);
    }

    @Test(expectedExceptions = IncorrectPasswordException.class)
    public void testLoginUserPasswordEmpty() throws IncorrectPasswordException {
        when(userDao.getUserById(user1.getUserId())).thenReturn(user1);
        userService.loginUser(user1, "");
    }

    @Test(expectedExceptions = IncorrectPasswordException.class)
    public void testLoginUserUserNull() throws IncorrectPasswordException {
        when(userDao.getUserById(user1.getUserId())).thenReturn(null);
        userService.loginUser(user1, "password123");
    }

    @Test
    public void testUpdateUser() {
        userService.updateUser(user1);
        verify(userDao).updateUser(user1);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(user1);
        verify(userDao).deleteUser(user1);
    }
}
