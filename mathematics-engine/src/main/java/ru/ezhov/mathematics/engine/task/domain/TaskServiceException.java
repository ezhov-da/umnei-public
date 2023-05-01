package ru.ezhov.mathematics.engine.task.domain;

public class TaskServiceException extends Exception {
    TaskServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
