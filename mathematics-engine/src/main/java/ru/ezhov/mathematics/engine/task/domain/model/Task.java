package ru.ezhov.mathematics.engine.task.domain.model;

import ru.ezhov.mathematics.engine.example.domain.model.PlainExample;

public class Task {
    private final HiddenValueNumber hiddenValueNumber;

    private final Value firstValue;
    private final Value secondValue;
    private final Value thirdValue;

    private final MathOperator mathOperator;

    private Task(HiddenValueNumber hiddenValueNumber, Value firstValue, Value secondValue, Value thirdValue, MathOperator mathOperator) {
        this.hiddenValueNumber = hiddenValueNumber;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.mathOperator = mathOperator;
    }

    public static Task create(HiddenValueNumber hiddenValueNumber, PlainExample example) throws UnsupportedMathOperatorException {
        return new Task(
                hiddenValueNumber,
                Value.create(example.first().value()),
                Value.create(example.second().value()),
                Value.create(example.result().value()),
                MathOperator.by(example.mathOperator().symbol())
        );
    }

    public HiddenValueNumber hiddenValueNumber() {
        return hiddenValueNumber;
    }

    public Value firstValue() {
        return firstValue;
    }

    public Value secondValue() {
        return secondValue;
    }

    public Value thirdValue() {
        return thirdValue;
    }

    public MathOperator mathOperator() {
        return mathOperator;
    }

    public Value by(HiddenValueNumber hiddenValueNumber) {
        switch (hiddenValueNumber) {
            case FIRST:
                return firstValue;
            case SECOND:
                return secondValue;
            case THIRD:
                return thirdValue;
            default:
                throw new UnsupportedHiddenValueNumberExeption("'" + hiddenValueNumber + "' не поддерживается");
        }
    }
}
