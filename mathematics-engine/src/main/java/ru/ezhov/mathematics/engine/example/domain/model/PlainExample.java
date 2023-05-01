package ru.ezhov.mathematics.engine.example.domain.model;

import java.util.Objects;

public class PlainExample {
    private final FirstValue first;
    private final MathOperator mathOperator;
    private final SecondValue second;
    private final ResultValue result;

    private PlainExample(FirstValue first, MathOperator mathOperator, SecondValue second, ResultValue result) {
        this.first = first;
        this.mathOperator = mathOperator;
        this.second = second;
        this.result = result;
    }

    public static PlainExample create(FirstValue first, MathOperator mathOperator, SecondValue second, ResultValue result) {
        return new PlainExample(
                first,
                mathOperator,
                second,
                result
        );
    }

    public FirstValue first() {
        return first;
    }

    public MathOperator mathOperator() {
        return mathOperator;
    }

    public SecondValue second() {
        return second;
    }

    public ResultValue result() {
        return result;
    }

    public String asString() {
        return String.format("%s %s %s = %s", first.value(), mathOperator.symbol(), second.value(), result.value());
    }

    public PlainExample resultAsFirstValue() {
        MathOperator convertMathOperator;

        switch (mathOperator) {
            case ADDITION:
                convertMathOperator = MathOperator.SUBTRACTION;
                break;
            case SUBTRACTION:
                convertMathOperator = MathOperator.ADDITION;
                break;
            case MULTIPLICATION:
                convertMathOperator = MathOperator.DIVISION;
                break;
            case DIVISION:
                convertMathOperator = MathOperator.MULTIPLICATION;
                break;
            default:
                throw new UnsupportedMathOperatorException("Неподдерживаемый оператор");
        }

        return new PlainExample(FirstValue.create(result.value()), convertMathOperator, second, ResultValue.create(first.value()));
    }

    public PlainExample resultAsSecondValue() {
        PlainExample plainExample;

        switch (mathOperator) {
            case ADDITION:
                plainExample = new PlainExample(FirstValue.create(result.value()), MathOperator.SUBTRACTION, SecondValue.create(first.value()), ResultValue.create(second.value()));
                break;
            case SUBTRACTION:
                plainExample = new PlainExample(FirstValue.create(first.value()), MathOperator.SUBTRACTION, SecondValue.create(result.value()), ResultValue.create(second.value()));
                break;
            case MULTIPLICATION:
                plainExample = new PlainExample(FirstValue.create(result.value()), MathOperator.DIVISION, SecondValue.create(first.value()), ResultValue.create(second.value()));
                break;
            case DIVISION:
                plainExample = new PlainExample(FirstValue.create(first.value()), MathOperator.DIVISION, SecondValue.create(result.value()), ResultValue.create(second.value()));
                break;
            default:
                throw new UnsupportedMathOperatorException("Неподдерживаемый оператор");
        }

        return plainExample;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainExample that = (PlainExample) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(mathOperator, that.mathOperator) &&
                Objects.equals(second, that.second) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, mathOperator, second, result);
    }
}
