package ru.ezhov.mathematics.engine.task.domain.model;

public class MaxValue {
    private final int value;

    private MaxValue(int value) {
        this.value = value;
    }

    public static MaxValue create(int value) {
        return new MaxValue(value);
    }

    public int value() {
        return value;
    }
}
