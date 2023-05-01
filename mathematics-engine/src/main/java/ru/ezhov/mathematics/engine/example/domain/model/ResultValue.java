package ru.ezhov.mathematics.engine.example.domain.model;

import java.util.Objects;

public class ResultValue {

    private int value;

    private ResultValue(int value) {
        this.value = value;
    }

    public static ResultValue create(int value) {
        return new ResultValue(value);
    }

    public static ResultValue create(String value) {
        return new ResultValue(Integer.parseInt(value));
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultValue that = (ResultValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
