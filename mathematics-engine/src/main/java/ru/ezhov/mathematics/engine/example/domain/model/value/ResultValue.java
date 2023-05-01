package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.Result;
import ru.ezhov.mathematics.engine.example.domain.model.Type;

public class ResultValue implements Result {

    private String value;

    private ResultValue(String value) {
        this.value = value;
    }

    public static ResultValue create(String value) {
        return new ResultValue(value);
    }

    public static ResultValue create(int value) {
        return new ResultValue(value + "");
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Type type() {
        return Type.RESULT;
    }
}
