package ru.ezhov.umnei.mathematics.application;

public class MathematicsApplicationException extends Exception {
    MathematicsApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    MathematicsApplicationException(String message) {
        super(message);
    }
}
