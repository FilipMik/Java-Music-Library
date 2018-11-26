package cz.muni.fi.pa165.music_library.exceptions;

/**
 * @author Filip Mik on 20. 11. 2018
 */

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
