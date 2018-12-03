package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.base.BaseFacadeTest;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.dto.UserLevelDto;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.IncorrectPasswordException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import cz.muni.fi.pa165.music_library.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Jan Ficko
 */

public class UserFacadeTest extends BaseFacadeTest {

    @Autowired
    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;
    private List<User> userList = new ArrayList<>();

    private UserDto userDto;
    private List<UserDto> userDtoList = new ArrayList<>();

    private UserAuthenticateDto userAuthenticateDto;

    private Long userId = 1L;
    private String username = "Username";
    private String email = "user@mail.muni.cz";

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(userFacade, "userService", userService);
        ReflectionTestUtils.setField(userFacade, "beanMappingService", beanMappingService);

        Date date = new Date();

        String encodedPassword = passwordEncoder.encode("Password");

        user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setDateCreated(date);
        user.setUserLevel(UserLevel.BasicUser);
        user.setPassword(encodedPassword);

        userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setDateCreated(date);
        userDto.setUserLevel(UserLevelDto.BasicUser);
        userDto.setPassword(encodedPassword);

        userAuthenticateDto = new UserAuthenticateDto();
        userAuthenticateDto.setUserId(userId);
        userAuthenticateDto.setPassword(encodedPassword);

        userList.add(user);
        userDtoList.add(userDto);
    }

    @Test
    public void getAllUsersTest() {
        when(userService.getAllUsers()).thenReturn(userList);
        when(beanMappingService.mapTo(userList, UserDto.class)).thenReturn(userDtoList);

        List<UserDto> users = userFacade.getAllUsers();

        assertThat(users).isNotNull();
        assertThat(user.getUsername()).isEqualTo(users.get(0).getUsername());
        verify(userService).getAllUsers();
        assertNotEquals(users.size(), 0);
        assertTrue(users.contains(userDto));
    }

    @Test
    public void findUserByIdTest() {
        when(userService.findUserById(userId)).thenReturn(user);
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);

        UserDto foundUser = userFacade.findUserById(userId);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUserId()).isEqualTo(userId);
        verify(userService).findUserById(userId);
        verify(beanMappingService).mapTo(user, UserDto.class);
    }

    @Test
    public void findUserByUsernameTest() {
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);
        when(userService.findUserByUsername(username)).thenReturn(user);

        UserDto foundUser = userFacade.findUserByUsername(username);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(username);
        verify(userService).findUserByUsername(username);
        verify(beanMappingService).mapTo(user, UserDto.class);
    }

    @Test
    public void findUserByEmailTest() {
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);
        when(userService.findUserByEmail(email)).thenReturn(user);

        UserDto foundUser = userFacade.findUserByEmail(email);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
        verify(userService).findUserByEmail(email);
        verify(beanMappingService).mapTo(user, UserDto.class);
    }

    @Test
    public void registerUserTest() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        when(beanMappingService.mapTo(userDto, User.class)).thenReturn(user);

        userFacade.registerUser(userDto, "Password");

        verify(userService).registerUser(user, "Password");
        verify(beanMappingService).mapTo(userDto, User.class);
    }

    @Test
    public void updateUserTest() {
        UserDto userUpdatedDto = userDto;
        userUpdatedDto.setUsername("Updated");
        User userUpdated = user;
        userUpdated.setUsername("Updated");

        when(beanMappingService.mapTo(userDto, User.class)).thenReturn(userUpdated);

        userFacade.updateUser(userUpdatedDto);

        verify(userService).updateUser(userUpdated);
        verify(beanMappingService).mapTo(userDto, User.class);
    }

    @Test
    public void deleteUserTest() {
        when(beanMappingService.mapTo(userDto, User.class)).thenReturn(user);

        userFacade.deleteUser(userDto);

        verify(userService).deleteUser(user);
    }

    @Test
    public void authenticationTest() throws IncorrectPasswordException {
        when(userService.findUserById(userId)).thenReturn(user);
        when(userService.loginUser(user, user.getPassword())).thenReturn(true);

        when(beanMappingService.mapTo(userDto, UserAuthenticateDto.class)).thenReturn(userAuthenticateDto);
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);

        assertTrue(userFacade.authenticate(userAuthenticateDto));
    }

    @Test
    public void changePasswordTest() {
        when(beanMappingService.mapTo(userDto, User.class)).thenReturn(user);

        userFacade.changeUserPassword(userDto, "newPassword");

        verify(userService).changeUserPassword(user, "newPassword");
        verify(beanMappingService).mapTo(userDto, User.class);
    }

    @Test
    public void isAdminTest() {
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);

        assertFalse(userFacade.isAdmin(userDto));
    }

}
