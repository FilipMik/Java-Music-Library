package cz.muni.fi.pa165.music_library.dao.interfaces;

import cz.muni.fi.pa165.music_library.data.entities.User;

import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public interface UserDao {

    /**
     * Get all users from the system database
     * @return List of all users found in database, empty list if there are no users
     */
    List<User> getAllUsers();

    /**
     * Find user with given id and return it
     * @param userId Id of searched user
     * @return User object with given id
     */
    User getUserById(int userId);

    /**
     * Find user with given name and return it
     * @param username Id of searched user
     * @return User object with given username
     */
    User getUserByName(int username);

    /**
     * Adds new user to the database
     * @param user User object to be added to database
     * @return True if user was added, false if there is already this user in database
     */
    Boolean AddUser(User user);

    /**
     * Delete user with given id from databse
     * @param userId Id of searched user
     * @return True if user was found and removed, false if there is no user with given id
     */
    Boolean deleteUser(int userId);

    /**
     * Update email of user with given userId
     * @param userId Id of user that will be updated
     * @param email New email of user
     * @return True if user was found and update, false is there in no user with given id
     */
    Boolean updateUserEmail(int userId, String email);
}
