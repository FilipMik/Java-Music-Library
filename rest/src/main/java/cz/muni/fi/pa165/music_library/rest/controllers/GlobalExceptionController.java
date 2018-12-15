package cz.muni.fi.pa165.music_library.rest.controllers;

import cz.muni.fi.pa165.music_library.rest.ApiError;
import cz.muni.fi.pa165.music_library.rest.exceptions.ResourceAlreadyExistingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/**
 * This shows how a @ControllerAdvice can be used so that all the 
 * handlers can be managed from a central location.
 * It is also possible to associate one handler to a set of Controllers
 * See https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ControllerAdvice.html
 * 
 * @author Jan Ficko
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ApiError handleException(ResourceAlreadyExistingException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("The requested resource already exists"));
        return apiError;
    }
}
