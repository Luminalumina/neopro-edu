package one.neopro.edu.neoproedu.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    ErrorDTO errorDTO = new ErrorDTO();


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "")
    public ErrorDTO handleNotFoundException(NotFoundException ex) {
        errorDTO.setErrorCode("01");
        errorDTO.setErrorMessage("Client not found");
        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleIllegalArgumentException(IllegalArgumentException ex) {
        errorDTO.setErrorCode("02");
        errorDTO.setErrorMessage("Some parameters are invalid");
        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        errorDTO.setErrorCode("03");
        errorDTO.setErrorMessage("Not valid data");
        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleConstraintViolationException(ConstraintViolationException ex) {
        errorDTO.setErrorCode("04");
        errorDTO.setErrorMessage("Name must only contain latin letters");
        return errorDTO;
    }
}


