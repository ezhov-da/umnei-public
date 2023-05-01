package ru.ezhov.mathematics.engine.example.domain.model;

public class UnsupportedMathOperatorException extends RuntimeException {
    UnsupportedMathOperatorException(String message) {
        super(message);
    }
}
