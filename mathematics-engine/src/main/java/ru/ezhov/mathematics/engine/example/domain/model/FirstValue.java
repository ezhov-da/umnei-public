package ru.ezhov.mathematics.engine.example.domain.model;

public class FirstValue {
    private int value;

    private FirstValue(int value) {
        this.value = value;
    }

    public static FirstValue create(int value) {
        return new FirstValue(value);
    }

    public int value() {
        return value;
    }
}
