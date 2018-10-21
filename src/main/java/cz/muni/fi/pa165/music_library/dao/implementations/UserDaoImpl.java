package cz.muni.fi.pa165.music_library.dao.implementations;

import cz.muni.fi.pa165.music_library.dao.interfaces.UserDao;
import cz.muni.fi.pa165.music_library.data.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Mik on 21. 10. 2018
 */

public class UserDaoImpl implements UserDao {

    private List<User> users = new ArrayList<>();

    public UserDaoImpl() {
        // TODO implement
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(int userId) {
        // TODO implement
        return null;
    }

    @Override
    public User getUserByName(int username) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean AddUser(User user) {
        // TODO implement
        return null;
    }

    @Override
    public Boolean deleteUser(User user) {
        // TODO implement
        return null;
    }

    @Override
    public void updateUserEmail(int userId, String email) {
        // TODO implement
    }
}
