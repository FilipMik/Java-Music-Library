package cz.muni.fi.pa165.music_library.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jan Ficko
 */

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists")
public class ResourceAlreadyExistingException extends RuntimeException {
    
}
