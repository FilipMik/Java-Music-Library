package cz.muni.fi.pa165.music_library.service;

import cz.muni.fi.pa165.music_library.dao.interfaces.UserDao;
import cz.muni.fi.pa165.music_library.data.entities.User;
import cz.muni.fi.pa165.music_library.data.entities.UserLevel;
import cz.muni.fi.pa165.music_library.exceptions.EmailAlreadyExsistsException;
import cz.muni.fi.pa165.music_library.exceptions.IncorrectPasswordException;
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
    public User findUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean isUserAdmin(User user) {
        return user.getUserLevel() == UserLevel.Admin;
    }

    @Override
    public void registerUser(User user, String password)
            throws EmailAlreadyExsistsException, UsernameAlreadyExsistsException {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExsistsException("Email already exists!");
        } else if (userDao.getUserByName(user.getUsername()) != null) {
            throw new UsernameAlreadyExsistsException("Username already exists!");
        } else {
            user.setPassword(passwordEncoder.encode(password));
            user.setUserLevel(UserLevel.BasicUser);
            userDao.createUser(user);
        }
    }

    @Override
    public boolean loginUser(User user, String password) throws IncorrectPasswordException {
        if (password.isEmpty() || userDao.getUserById(user.getUserId()) == null) {
            throw new IncorrectPasswordException("Wrong name or password");
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
