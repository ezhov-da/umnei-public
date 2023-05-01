package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.MathOperator;
import ru.ezhov.mathematics.engine.example.domain.model.MathOperatorType;
import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class DivisionOperatorValue implements Value, MathOperatorType {

    private DivisionOperatorValue() {
    }

    public static DivisionOperatorValue create() {
        return new DivisionOperatorValue();
    }

    @Override
    public String value() {
        return MathOperator.DIVISION.symbol();
    }

    @Override
    public Type type() {
        return Type.OPERATOR;
    }

    @Override
    public MathOperator operatorType() {
        return MathOperator.DIVISION;
    }
}