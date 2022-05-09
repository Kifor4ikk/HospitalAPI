package ru.kifor4ik.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.kifor4ik.exception.ExceptionDto;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String notFound(Exception exception, WebRequest request) {
        return "Message: " + exception.getMessage() + "\nLocalized message: " + exception.getLocalizedMessage()
                + "\nCause: " + exception.getCause();
    }

}
