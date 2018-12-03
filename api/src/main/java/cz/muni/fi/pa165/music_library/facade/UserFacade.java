package cz.muni.fi.pa165.music_library.facade;


import cz.muni.fi.pa165.music_library.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.music_library.dto.UserDto;

import java.util.List;

/**
 * @author Jan Ficko
 */
public interface UserFacade {

    List<UserDto> getAllUsers();

    UserDto findUserById(Long userId);

    UserDto findUserByUsername(String username);

    UserDto findUserByEmail(String email);

    void registerUser(UserDto userDto, String password);

    void updateUser(UserDto userDto);

    void deleteUser(UserDto userDto);

    boolean authenticate(UserAuthenticateDto userAuthenticate);

    void changeUserPassword(UserDto userDto, String newPassword);

    boolean isAdmin(UserDto userDto);

}
