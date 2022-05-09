package ru.kifor4ik.exception;

public class BadDateException extends RuntimeException{
    public BadDateException(String message) {
        super(message);
    }
}
