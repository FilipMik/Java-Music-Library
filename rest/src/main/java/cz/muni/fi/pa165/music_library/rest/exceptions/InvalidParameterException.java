package cz.muni.fi.pa165.music_library.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jan Ficko
 */

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidParameterException extends RuntimeException {
    
} 
