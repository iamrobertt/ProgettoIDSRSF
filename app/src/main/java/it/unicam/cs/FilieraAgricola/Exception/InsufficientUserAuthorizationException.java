package it.unicam.cs.FilieraAgricola.Exception;

public class InsufficientUserAuthorizationException extends RuntimeException{

    public InsufficientUserAuthorizationException(String errorMessage) {
        super(errorMessage);
    }
}
