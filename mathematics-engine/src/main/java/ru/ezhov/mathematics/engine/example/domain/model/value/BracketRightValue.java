package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class BracketRightValue implements Value {

    private BracketRightValue() {
    }

    public static BracketRightValue create() {
        return new BracketRightValue();
    }

    @Override
    public String value() {
        return ")";
    }

    @Override
    public Type type() {
        return Type.BRACKET_RIGHT;
    }
}
