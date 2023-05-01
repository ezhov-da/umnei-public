package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class EqualSignValue implements Value {

    private EqualSignValue() {
    }

    public static EqualSignValue create() {
        return new EqualSignValue();
    }

    @Override
    public String value() {
        return "=";
    }

    @Override
    public Type type() {
        return Type.EQUAL_SIGN;
    }
}
