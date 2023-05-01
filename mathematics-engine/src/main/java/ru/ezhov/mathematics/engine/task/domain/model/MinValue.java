package ru.ezhov.mathematics.engine.task.domain.model;

public class MinValue {
    int value;

    private MinValue(int value) {
        this.value = value;
    }

    public static MinValue create(int value) {
        return new MinValue(value);
    }

    public int value() {
        return value;
    }
}
