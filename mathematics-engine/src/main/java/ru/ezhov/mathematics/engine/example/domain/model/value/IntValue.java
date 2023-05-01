package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class IntValue implements Value {

    private final String value;

    private IntValue(String value) {
        this.value = value;
    }

    public static IntValue create(String value) {
        return new IntValue(value);
    }

    public static IntValue create(int value) {
        return new IntValue(value + "");
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Type type() {
        return Type.VALUE;
    }
}
