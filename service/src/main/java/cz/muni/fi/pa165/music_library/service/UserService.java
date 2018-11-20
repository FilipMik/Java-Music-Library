package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExsistsException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExsistsException;
import org.springframework.stereotype.Service;

/**
 * @author Filip Mik on 20. 11. 2018
 */

@Service
public interface UserService {

    User findUserById(Long userId);

    User findUserByUsername(String username);

    void registerUser(User user, String password) throws EmailAlreadyExsistsException, UsernameAlreadyExsistsException;

    void loginUser(User user, String password);

    void changeUserPassword(User user, String oldPassword, String newPassword);
}
