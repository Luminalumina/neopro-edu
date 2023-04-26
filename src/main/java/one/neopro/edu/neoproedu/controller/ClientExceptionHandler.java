package one.neopro.edu.neoproedu.controller;

import jakarta.validation.ConstraintViolationException;
import one.neopro.edu.neoproedu.DTO.ErrorDTO;
import one.neopro.edu.neoproedu.exception.ArgumentNotValidException;
import one.neopro.edu.neoproedu.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientExceptionHandler {

    ErrorDTO errorDTO = new ErrorDTO();


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleNotFoundException(NotFoundException ex) {
        errorDTO.setErrorCode("01");
        errorDTO.setErrorMessage("Client not found");
        errorDTO.setSystemErrorMessage(ex.getMessage());

        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleIllegalArgumentException(IllegalArgumentException ex) {
        errorDTO.setErrorCode("02");
        errorDTO.setErrorMessage("Some parameters are invalid");
        errorDTO.setSystemErrorMessage(ex.getMessage());

        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        errorDTO.setErrorCode("03");
        errorDTO.setErrorMessage("Not valid data");
        errorDTO.setSystemErrorMessage(ex.getMessage());
        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleConstraintViolationException(ConstraintViolationException ex) {
        errorDTO.setErrorCode("04");
        errorDTO.setErrorMessage("Name must only contain letters");
        errorDTO.setSystemErrorMessage(ex.getMessage());

        return errorDTO;
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleArgumentNotValidException(ArgumentNotValidException ex) {
        errorDTO.setErrorCode("05");
        errorDTO.setErrorMessage("Name must only contain letters");
        errorDTO.setSystemErrorMessage(ex.getMessage());
        return errorDTO;
    }
}


