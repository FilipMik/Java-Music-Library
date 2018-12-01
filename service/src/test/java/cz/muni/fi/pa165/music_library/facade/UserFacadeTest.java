package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.base.BaseFacadeTest;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.dto.UserLevelDto;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import cz.muni.fi.pa165.music_library.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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

public class UserFacadeTest extends BaseFacadeTest {

    @Autowired
    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserService userService;

    private User user;
    private List<User> userList = new ArrayList<>();

    private UserDto userDto;
    private List<UserDto> userDtoList = new ArrayList<>();

    private UserAuthenticateDto userAuthenticateDto;

    private Long userId = 1L;
    private String username = "Username";
    private String email = "user@mail.muni.cz";
    private String password = "Password";

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(userFacade, "userService", userService);
        ReflectionTestUtils.setField(userFacade, "beanMappingService", beanMappingService);

        Date date = new Date();

        user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setDateCreated(date);
        user.setUserLevel(UserLevel.BasicUser);
        user.setPassword(password);

        userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setDateCreated(date);
        userDto.setUserLevel(UserLevelDto.BasicUser);
        userDto.setPassword(password);

        userAuthenticateDto = new UserAuthenticateDto();
        userAuthenticateDto.setUserId(userId);
        userAuthenticateDto.setPassword(password);

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
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);
        when(userService.findUserById(userId)).thenReturn(user);

        UserDto userDto = userFacade.findUserById(userId);

        assertThat(userDto).isNotNull();
        assertThat(userDto.getUserId()).isEqualTo(user.getUserId());
        verify(userService).findUserById(userId);
        verify(beanMappingService).mapTo(user, UserDto.class);
    }

    @Test
    public void findUserByUsernameTest() {
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);
        when(userService.findUserByUsername(username)).thenReturn(user);

        UserDto userDto = userFacade.findUserByUsername(username);

        assertThat(userDto).isNotNull();
        assertThat(userDto.getUsername()).isEqualTo(user.getUsername());
        verify(userService).findUserByUsername(username);
        verify(beanMappingService).mapTo(user, UserDto.class);
    }

    @Test
    public void findUserByEmailTest() {
        when(beanMappingService.mapTo(user, UserDto.class)).thenReturn(userDto);
        when(userService.findUserByEmail(email)).thenReturn(user);

        UserDto userDto = userFacade.findUserByEmail(email);

        assertThat(userDto).isNotNull();
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        verify(userService).findUserByEmail(email);
        verify(beanMappingService).mapTo(user, UserDto.class);
    }

    @Test
    public void registerUserTest() throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        when(beanMappingService.mapTo(userDto, User.class)).thenReturn(user);

        userFacade.registerUser(userDto, password);

        verify(userService).registerUser(user, password);
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
    public void authenticationTest() {
        when(beanMappingService.mapTo(userAuthenticateDto, User.class)).thenReturn(user);

        assertEquals(true, userFacade.authenticate(userAuthenticateDto));
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

        assertEquals(false, userFacade.isAdmin(userDto));
    }

}
