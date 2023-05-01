package ru.ezhov.mathematics.engine.task.domain.model;

public class Value {
    private final int value;

    private Value(int value) {
        this.value = value;
    }

    public static Value create(int value) {
        return new Value(value);
    }

    public int value() {
        return value;
    }
}
