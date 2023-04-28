package com.example.flowcalculator.exceptiom;

public class InvalidLoginDetailsException extends BusinessLogicException {
    public InvalidLoginDetailsException(String message) {
        super(message);
    }
}
