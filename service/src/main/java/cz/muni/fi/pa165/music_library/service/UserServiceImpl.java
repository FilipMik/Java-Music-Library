package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.UserDao;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExsistsException;
import cz.muni.fi.pa165.music_library.exceptions.UsernameAlreadyExsistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Filip Mik on 20. 11. 2018
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public void registerUser(User user, String password)
            throws EmailAlreadyExsistsException, UsernameAlreadyExsistsException {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExsistsException("Email already exists!");
        } else if (userDao.getUserByName(user.getUsername()) != null) {
            throw new UsernameAlreadyExsistsException("Username already exists!");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserLevel(UserLevel.BasicUser);
            userDao.createUser(user);
        }
    }

    @Override
    public void loginUser(User user, String password) {

    }

    @Override
    public void changeUserPassword(User user, String oldPassword, String newPassword) {

    }
}
