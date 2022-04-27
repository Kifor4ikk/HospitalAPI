package ru.kifor4ik.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionDto extends RuntimeException{

    public ExceptionDto(String message) {
        super(message);
    }

    public static ExceptionDto toExceptionDto(Exception exception){
        return new ExceptionDto(exception.getMessage());
    }
}
