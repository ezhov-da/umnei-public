package ru.ezhov.mathematics.engine.example.domain.model.value;

import ru.ezhov.mathematics.engine.example.domain.model.MathOperator;
import ru.ezhov.mathematics.engine.example.domain.model.MathOperatorType;
import ru.ezhov.mathematics.engine.example.domain.model.Type;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

public class MultiplicationOperatorValue  implements Value, MathOperatorType {

    private MultiplicationOperatorValue() {
    }

    public static MultiplicationOperatorValue create() {
        return new MultiplicationOperatorValue();
    }

    @Override
    public String value() {
        return MathOperator.MULTIPLICATION.symbol();
    }

    @Override
    public Type type() {
        return Type.OPERATOR;
    }

    @Override
    public MathOperator operatorType() {
        return MathOperator.MULTIPLICATION;
    }
}