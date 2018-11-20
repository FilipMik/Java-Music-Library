package cz.muni.fi.pa165.music_library.exceptions;

/**
 * @author Filip Mik on 20. 11. 2018
 */

public class UsernameAlreadyExsistsException extends Exception {
    public UsernameAlreadyExsistsException(String errorMessage) {
        super(errorMessage);
    }
}
