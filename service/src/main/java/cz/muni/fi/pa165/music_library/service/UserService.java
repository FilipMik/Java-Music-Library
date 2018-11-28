package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.IncorrectPasswordException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Filip Mik on 20. 11. 2018
 */

@Service
public interface UserService {

    User findUserById(Long userId);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    List<User> getAllUsers();

    boolean isUserAdmin(User user);

    boolean loginUser(User user, String password) throws IncorrectPasswordException;

    void registerUser(User user, String password) throws EmailAlreadyExistsException, UsernameAlreadyExistsException;

    void changeUserPassword(User user, String newPassword);

    void updateUser(User user);

    void deleteUser(User user);
}
