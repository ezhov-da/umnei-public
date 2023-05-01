package ru.ezhov.mathematics.engine.example.domain.model;

import ru.ezhov.mathematics.engine.example.domain.model.value.AdditionOperatorValue;
import ru.ezhov.mathematics.engine.example.domain.model.value.DivisionOperatorValue;
import ru.ezhov.mathematics.engine.example.domain.model.value.MultiplicationOperatorValue;
import ru.ezhov.mathematics.engine.example.domain.model.value.SubtractionOperatorValue;

public class MathOperatorTypeFactory {
    public static MathOperatorType by(String symbol) throws UnsupportedMathOperatorTypeException {
        final MathOperator mathOperator = MathOperator.by(symbol);

        switch (mathOperator) {
            case ADDITION:
                return AdditionOperatorValue.create();
            case SUBTRACTION:
                return SubtractionOperatorValue.create();
            case DIVISION:
                return DivisionOperatorValue.create();
            case MULTIPLICATION:
                return MultiplicationOperatorValue.create();
            default:
                throw new UnsupportedMathOperatorTypeException("Неподдерживаемый тип '" + symbol + "'");
        }
    }
}
