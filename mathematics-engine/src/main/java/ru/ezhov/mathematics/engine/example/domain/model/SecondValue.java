package ru.ezhov.mathematics.engine.example.domain.model;

public class SecondValue {
    private int value;

    private SecondValue(int value) {
        this.value = value;
    }

    public static SecondValue create(int value) {
        return new SecondValue(value);
    }

    public int value() {
        return value;
    }
}
