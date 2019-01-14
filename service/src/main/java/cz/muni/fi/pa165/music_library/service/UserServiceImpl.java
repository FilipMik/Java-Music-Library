package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.UserDao;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExistsException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Filip Mik on 20. 11. 2018
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean isUserAdmin(User user) {
        return user.getUserLevel() == UserLevel.ADMIN;
    }

    @Override
    public void registerUser(User user, String password)
            throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email already exists!");
        } else if (userDao.getUserByName(user.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Username already exists!");
        } else {
            user.setPassword(passwordEncoder.encode(password));
            userDao.createUser(user);
        }
    }

    @Override
    public boolean loginUser(User user, String password) {
        if (user == null ||userDao.getUserByEmail(user.getEmail()) == null) {
            return false;
        } else {
            return passwordEncoder.matches(password, user.getPassword());
        }
    }

    @Override
    public void changeUserPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userDao.updateUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
