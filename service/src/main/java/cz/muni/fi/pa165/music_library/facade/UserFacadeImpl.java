package cz.muni.fi.pa165.music_library.facade;

import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.IncorrectPasswordException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import cz.muni.fi.pa165.music_library.service.BeanMappingService;
import cz.muni.fi.pa165.music_library.service.TimeService;
import cz.muni.fi.pa165.music_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Filip Mik on 29. 11. 2018
 */

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private TimeService timeService;

    @Override
    public List<UserDto> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDto.class);
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user = userService.findUserById(userId);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User user = userService.findUserByUsername(username);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDto.class);
    }

    @Override
    public void registerUser(UserDto userDto, String password) {
        User user = beanMappingService.mapTo(userDto, User.class);
        user.setDateCreated(timeService.getCurrentDate());
        try {
            userService.registerUser(user, password);
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserDto userDto) {
        userService.updateUser(beanMappingService.mapTo(userDto, User.class));
    }

    @Override
    public void deleteUser(UserDto userDto) {
        userService.deleteUser(beanMappingService.mapTo(userDto, User.class));
    }

    @Override
    public boolean authenticate(UserAuthenticateDto userAuthenticate) {
        try {
            return userService.loginUser(userService.findUserByEmail(userAuthenticate.getEmail()),
                    userAuthenticate.getPassword());
        } catch (IncorrectPasswordException e) {
            return false;
        }
    }

    @Override
    public void changeUserPassword(UserDto userDto, String newPassword) {
        userService.changeUserPassword(beanMappingService.mapTo(userDto, User.class), newPassword);
    }

    @Override
    public boolean isAdmin(UserDto userDto) {
        return userService.isUserAdmin(beanMappingService.mapTo(userDto, User.class));
    }
}
