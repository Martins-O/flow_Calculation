package com.example.flowcalculator.exceptiom;

public class UsernameAlreadyExistsException extends BusinessLogicException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
