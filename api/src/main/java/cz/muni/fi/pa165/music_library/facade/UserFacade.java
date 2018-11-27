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

    void registerUser(UserDto user);

    void updateUser(UserDto user);

    void deleteUser(Long userId);

    boolean authenticate(UserAuthenticateDto userAuthenticate);

    void changeUserPassword(UserDto user, String newPassword);

    boolean isAdmin(UserDto user);

}
