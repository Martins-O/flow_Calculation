package com.example.flowcalculator.exceptiom;

public class UnauthorizedRequestException extends BusinessLogicException {
    public UnauthorizedRequestException(String message) {
        super(message);
    }
}
