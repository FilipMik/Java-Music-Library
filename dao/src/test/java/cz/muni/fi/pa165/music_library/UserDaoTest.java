package cz.muni.fi.pa165.music_library;

import cz.muni.fi.pa165.music_library.dao.interfaces.PlayListDao;
import cz.muni.fi.pa165.music_library.dao.interfaces.UserDao;
import cz.muni.fi.pa165.music_library.data.entities.Playlist;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jan Ficko
 */

@ContextConfiguration(classes = ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PlayListDao playListDao;

    private User userOne;
    private User userTwo;

    private Playlist playListOne;
    private Playlist playListTwo;

    /**
     * Instantiate two user objects that can be used throughout this test class.
     */
    @Before
    public void init() {
        userOne = new User();
        userOne.setUsername("Test1");
        userOne.setEmail("test.one@mail.muni.cz");
        userOne.setDateCreated(new Date());
        userOne.setUserLevel(UserLevel.BasicUser);
        userOne.setPassword("halabala");

        userTwo = new User();
        userTwo.setUsername("Test2");
        userTwo.setEmail("test.two@mail.muni.cz");
        userTwo.setDateCreated(new Date());
        userTwo.setUserLevel(UserLevel.Admin);
        userTwo.setPassword("halabala");

        playListOne = new Playlist();
        playListOne.setTitle("Playlist One");
        playListOne.setDateCreated(new Date());

        playListTwo = new Playlist();
        playListTwo.setTitle("Playlist Two");
        playListTwo.setDateCreated(new Date());
    }

    /**
     * Test if user is inserted into the database.
     */
    @Test
    public void testUserPersistence() {
        userDao.createUser(userOne);

        assertEquals("Expected one user", 1, userDao.getAllUsers().size());
    }

    /**
     * Test if exception is thrown when null object is inserted.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPersistenceWhenUserObjectIsNull_Exception() {
        userDao.createUser(null);

        assertEquals("Expected zero users", 0, userDao.getAllUsers().size());
    }

    /**
     * Test if exception is thrown if there is no username set.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPersistenceWhenUsernameIsNull_Exception() {
        userOne.setUsername(null);

        userDao.createUser(userOne);
    }

    /**
     * Test if exception is thrown if there is no username set.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testShortPassword() {
        userOne.setPassword("rasca");
        userDao.createUser(userOne);
    }

    /**
     * Test if exception is thrown if no email is set.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPersistenceWhenEmailIsNull_Exception() {
        userOne.setEmail(null);
        userDao.createUser(userOne);
    }

    /**
     * Test if user when adding playlist that is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPersistenceWhenPlaylistIsNull() {
        userOne.addPlaylist(null);
        userDao.createUser(userOne);
    }

    @Test
    public void findUserByIdTest() {
        userDao.createUser(userTwo);
        Assert.assertNotNull(userDao.getUserByEmail("test.two@mail.muni.cz"));
    }

    /**
     * Test if both objects, that are inserted, are retrieved when method getAllUsers is called.
     */
    @Test
    public void testGetUsers_EqualsTwo() {
        Date currentDate = new Date();
        Date tomorrowsDate = new Date(System.currentTimeMillis() + 86400000);

        userOne.setDateCreated(currentDate);
        userDao.createUser(userOne);

        userTwo.setDateCreated(tomorrowsDate);
        userDao.createUser(userTwo);

        List<User> users = userDao.getAllUsers();

        assertEquals("Expected two users", 2, users.size());

        assertEquals(users.get(0).getUsername(), "Test1");
        assertEquals(users.get(0).getEmail(), "test.one@mail.muni.cz");
        assertEquals(users.get(0).getDateCreated(), currentDate);
        assertEquals(users.get(0).getUserLevel(), UserLevel.BasicUser);

        assertEquals(users.get(1).getUsername(), "Test2");
        assertEquals(users.get(1).getEmail(), "test.two@mail.muni.cz");
        assertEquals(users.get(1).getDateCreated(), tomorrowsDate);
        assertEquals(users.get(1).getUserLevel(), UserLevel.Admin);
    }

    /**
     * Test if you get the right user object from the getUserById method.
     */
    @Test
    public void testGetUserById() {
        userDao.createUser(userOne);

        List<User> userList = userDao.getAllUsers();

        assertEquals("Expected one user", 1, userList.size());

        User retrievedUser = userDao.getUserById(userList.get(0).getUserId());

        assertNotNull("Expected to retrieve user by ID", retrievedUser);
    }

    /**
     * Test if you get the right user object from the getUserByName method.
     */
    @Test
    public void testGetUserByUsername() {
        userDao.createUser(userOne);

        User retrievedUser = userDao.getUserByName("Test1");

        assertNotNull("Expected to retrieve at least one user", retrievedUser);
    }

    /**
     * Test if exception is thrown when trying to delete null user object.
     */
    @Test(expected = NullPointerException.class)
    public void testDeleteUserWhenUserObjectIsNull_Exception() {
        userDao.deleteUser(null);
    }

    /**
     * Test if user object is deleted from the database.
     */
    @Test
    public void testDeleteOneUser() {
        userDao.createUser(userOne);

        List<User> userList = userDao.getAllUsers();


        assertEquals("Expected one user", 1, userList.size());

        User retrievedUser = userDao.getUserById(userList.get(0).getUserId());

        assertEquals(userOne.getUsername(), retrievedUser.getUsername());

        userDao.deleteUser(retrievedUser);

        List<User> newUserList = userDao.getAllUsers();

        assertEquals("Expected zero users", 0, newUserList.size());
    }

    /**
     * Test if the right user object is deleted when there are two users in the database.
     */
    @Test
    public void testDeleteOneUserWithTwoInDatabase() {
        userDao.createUser(userOne);

        userDao.createUser(userTwo);

        List<User> userList = userDao.getAllUsers();

        assertEquals("Expected two users", 2, userList.size());

        User retrievedUser = userDao.getUserById(userList.get(1).getUserId());

        assertEquals(userTwo.getUsername(), retrievedUser.getUsername());

        userDao.deleteUser(retrievedUser);

        List<User> newUserList = userDao.getAllUsers();


        assertEquals("Expected one user", 1, newUserList.size());

        assertEquals(userOne.getUsername(), newUserList.get(0).getUsername());
    }

    /**
     * Test if user data is updated when using update method.
     */
    @Test
    public void testUpdateUser() {
        userDao.createUser(userOne);

        userDao.createUser(userTwo);

        User firstUserRetrieve = userDao.getUserByName("Test1");
        firstUserRetrieve.setUsername("Masarykova");
        userDao.updateUser(firstUserRetrieve);

        List<User> userList = userDao.getAllUsers();
        assertEquals("Expected two users", 2, userList.size());

        assertEquals(firstUserRetrieve.getUserId(), userList.get(0).getUserId());
    }

    /**
     * Test that one to many relation between Playlist and User is correct.
     */
    @Test
    public void testUserAndPlaylistRelation() {
        userOne.addPlaylist(playListOne);
        userOne.addPlaylist(playListTwo);

        playListOne.setUser(userOne);
        playListTwo.setUser(userTwo);

        playListDao.createPlaylist(playListOne);
        playListDao.createPlaylist(playListTwo);
        userDao.createUser(userOne);

        User retrievedUser = userDao.getUserById(userOne.getUserId());
        assertEquals("Expected 2 playlists connected to user", 2, retrievedUser.getPlaylists().size());

        Playlist retrievedPlaylistOne = retrievedUser.getPlaylists().get(0);
        Playlist retrievedPlaylistTwo = retrievedUser.getPlaylists().get(1);
        assertEquals("Expected reference to first playlist", playListOne, retrievedPlaylistOne);
        assertEquals("Expected reference to second playlist", playListTwo, retrievedPlaylistTwo);

        Playlist retrievedPlaylist = playListDao.getPlaylistById(playListOne.getPlaylistId());
        assertEquals("Test1", retrievedPlaylist.getUser().getUsername());
    }

}
