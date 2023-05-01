package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.MathOperator;
import ru.ezhov.mathematics.engine.example.domain.model.MathOperatorType;
import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class AdditionOperatorValue implements Value, MathOperatorType {

    private AdditionOperatorValue() {
    }

    public static AdditionOperatorValue create() {
        return new AdditionOperatorValue();
    }

    @Override
    public String value() {
        return MathOperator.ADDITION.symbol();
    }

    @Override
    public Type type() {
        return Type.OPERATOR;
    }

    @Override
    public MathOperator operatorType() {
        return MathOperator.ADDITION;
    }
}
