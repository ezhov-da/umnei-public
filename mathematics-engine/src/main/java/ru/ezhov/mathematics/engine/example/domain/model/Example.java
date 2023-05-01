package ru.ezhov.mathematics.engine.example.domain.model;

import ru.ezhov.mathematics.engine.example.domain.model.value.BracketLeftValue;
import ru.ezhov.mathematics.engine.example.domain.model.value.BracketRightValue;
import ru.ezhov.mathematics.engine.example.domain.model.value.EqualSignValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Example {
    private List<Value> values = new ArrayList<>();

    private Example(Value first, MathOperatorType mathOperator, Value second, Value result) {
        values.add(first);
        values.add(mathOperator);
        values.add(second);
        values.add(EqualSignValue.create());
        values.add(result);
    }

    private Example(List<Value> values) {
        this.values = Collections.unmodifiableList(values);
    }

    public static Example create(Value first, MathOperatorType mathOperator, Value second, Value result) {
        return new Example(first, mathOperator, second, result);
    }

    public List<AvailableValue> availableValues() {
        List<AvailableValue> availableValues = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            Value value = values.get(i);
            if (value.type() == Type.VALUE) {
                availableValues.add(new AvailableValue(i, value));
            }
        }

        return availableValues;
    }

    private static Example create(List<Value> values) {
        return new Example(values);
    }

    public Example combine(AvailableValue availableValue, Example example) {
        List<Value> newValues = new ArrayList<>();
        Optional<Value> resultOptional = example.result();
        if (resultOptional.isPresent()) {
            Value result = resultOptional.get();
            for (int i = 0; i < values.size(); i++) {
                Value value = values.get(i);

                if (value.type() == Type.VALUE && value.value().equals(result.value()) && i == availableValue.index()) {
                    newValues.add(BracketLeftValue.create());
                    List<Value> onlyExpression = example.onlyExpression();
                    newValues.addAll(onlyExpression);
                    newValues.add(BracketRightValue.create());
                } else {
                    newValues.add(value);
                }
            }
        }

        return Example.create(newValues);
    }

    private Optional<Value> result() {
        return values.stream().filter(v -> v.type() == Type.RESULT).findFirst();
    }

    public boolean contains(MathOperator mathOperator) {
        return values.stream().anyMatch(v -> v.type() == Type.OPERATOR && ((MathOperatorType) v).operatorType() == mathOperator);
    }

    private List<Value> onlyExpression() {
        return this.values.stream().filter(v -> v.type() != Type.RESULT && v.type() != Type.EQUAL_SIGN).collect(Collectors.toList());
    }

    public String asString() {
        String text = values.stream().map(Value::value).collect(Collectors.joining(" "));
        return text.replaceAll("\\( ", "(").replaceAll(" \\)", ")");
    }

    static class AvailableValue {
        private final int index;
        private final Value value;

        private AvailableValue(int index, Value value) {
            this.index = index;
            this.value = value;
        }

        private int index() {
            return index;
        }

        public Value value() {
            return value;
        }
    }


    //TODO: залепа сделанная для совместимости с предыдущей версией, необходимо переделатть WEB
    public String firstValue() {
        return values.stream().filter(v -> (v.type() == Type.VALUE)).findFirst().get().value();
    }

    public String secondValue() {
        return values.stream().filter(v -> (v.type() == Type.VALUE)).collect(Collectors.toList()).get(1).value();
    }

    public String thirdValue() {
        return values.stream().filter(v -> (v.type() == Type.RESULT)).findFirst().get().value();
    }

    public MathOperator mathOperator() {
        MathOperatorType mathOperatorType = (MathOperatorType) values.stream().filter(v -> (v.type() == Type.OPERATOR)).findFirst().get();
        return mathOperatorType.operatorType();
    }
}
