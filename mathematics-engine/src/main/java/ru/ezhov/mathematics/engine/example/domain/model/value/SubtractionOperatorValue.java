package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.MathOperator;
import ru.ezhov.mathematics.engine.example.domain.model.MathOperatorType;
import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class SubtractionOperatorValue  implements Value, MathOperatorType {

    private SubtractionOperatorValue() {
    }

    public static SubtractionOperatorValue create() {
        return new SubtractionOperatorValue();
    }

    @Override
    public String value() {
        return MathOperator.SUBTRACTION.symbol();
    }

    @Override
    public Type type() {
        return Type.OPERATOR;
    }

    @Override
    public MathOperator operatorType() {
        return MathOperator.SUBTRACTION;
    }
}