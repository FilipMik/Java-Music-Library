package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.music_library.dto.UserDto;
import cz.muni.fi.pa165.music_library.facade.UserFacade;
import cz.muni.fi.pa165.music_library.rest.ApiUris;
import cz.muni.fi.pa165.music_library.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Klara Minsterova
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_USER)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDto> getAllUsers() {
        logger.debug("GET getUsers()");

        return userFacade.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDto findUserById(@PathVariable("id") Long userId) {
        logger.debug("GET findUserById");

        UserDto userDto = userFacade.findUserById(userId);
        if (userDto != null) {
            return userDto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDto findUserByName(@PathVariable("name") String username) {
        logger.debug("GET findUserByName");

        UserDto userDto = userFacade.findUserByUsername(username);
        if (userDto != null) {
            return userDto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDto findUserByEmail(@PathVariable("email") String email) {
        logger.debug("GET findUserByEmail");

        UserDto userDto = userFacade.findUserByEmail(email);
        if (userDto != null) {
            return userDto;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void registerUser(@RequestBody UserDto userDto) {
        logger.debug("POST registerUser");

        userFacade.registerUser(userDto, userDto.getPassword());
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void updateUser(@RequestBody UserDto userDto) {
        logger.debug("PUT updateUser");

        userFacade.updateUser(userDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteUser(@PathVariable("id") Long userId) {
        logger.debug("DELETE deleteUser");

        UserDto userDto = userFacade.findUserById(userId);

        if (userDto == null) {
            throw new ResourceNotFoundException();
        } else {
            userFacade.deleteUser(userDto);
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final boolean authenticateUser(@RequestBody UserAuthenticateDto userAuthenticateDto) {
        logger.debug("POST authenticateUser");

        boolean result = userFacade.authenticate(userAuthenticateDto);
        return result;
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void changePassword(@RequestBody UserAuthenticateDto userChangeDto) {
        logger.debug("PUT changePassword");

        UserDto userDto = userFacade.findUserById(userChangeDto.getUserId());

        if (userDto == null) {
            throw new ResourceNotFoundException();
        } else {
            userFacade.changeUserPassword(userDto, userChangeDto.getPassword());
        }
    }

    @RequestMapping(value = "/{id}/isAdmin", method = RequestMethod.GET)
    public final boolean isAdmin(@PathVariable("id") Long userId) {
        logger.debug("GET isAdmin");

        UserDto userDto = userFacade.findUserById(userId);

        if (userDto == null) {
            throw new ResourceNotFoundException();
        } else {
            boolean result = userFacade.isAdmin(userDto);
            return result;
        }
    }
}
