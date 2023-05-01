package ru.ezhov.mathematics.engine.example.domain.model;

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

    public String symbol() {
        return symbol;
    }

    public static MathOperator by(String symbol) {
        final Optional<MathOperator> mathOperator = Stream.of(MathOperator.values()).filter(mo -> mo.symbol().equals(symbol)).findFirst();
        if(mathOperator.isPresent()){
            return mathOperator.get();
        } else {
            throw new UnsupportedMathOperatorException("Неподдерживаемый оператор '" + symbol + "'");
        }
    }
}
