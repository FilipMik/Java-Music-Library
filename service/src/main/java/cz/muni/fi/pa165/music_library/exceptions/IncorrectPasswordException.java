package cz.muni.fi.pa165.music_library.exceptions;

/**
 * @author Filip Mik on 21. 11. 2018
 */

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
