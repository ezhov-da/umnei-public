package ru.ezhov.mathematics.engine.task.domain.model;

import java.util.Optional;
import java.util.stream.Stream;

public enum MathOperator {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION(":");

    private final String symbol;

    MathOperator(String symbol) {
        this.symbol = symbol;
    }

    public static MathOperator by(String symbol) throws UnsupportedMathOperatorException {
        Optional<MathOperator> any = Stream.of(MathOperator.values()).filter(m -> m.symbol().equals(symbol)).findAny();
        if (any.isPresent()) {
            return any.get();
        }
        throw new UnsupportedMathOperatorException("'" + symbol + "' неподдерживавемый оператор");
    }

    public String symbol() {
        return symbol;
    }
}
