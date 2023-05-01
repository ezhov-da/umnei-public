package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class BracketLeftValue implements Value {

    private BracketLeftValue() {
    }

    public static BracketLeftValue create() {
        return new BracketLeftValue();
    }

    @Override
    public String value() {
        return "(";
    }

    @Override
    public Type type() {
        return Type.BRACKET_RIGHT;
    }
}
