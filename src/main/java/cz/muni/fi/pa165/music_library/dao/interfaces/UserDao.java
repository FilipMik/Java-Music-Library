package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.User;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(int userId);

    User getUserByName(int username);

    Boolean AddUser(User user);

    Boolean deleteUser(User user);

    void updateUserEmail(int userId, String email);
}
