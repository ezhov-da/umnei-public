package ru.ezhov.mathematics.engine.example.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FirstValueGroup {
    private final List<PlainExample> plainExamples;

    public FirstValueGroup(List<PlainExample> plainExamples) {
        this.plainExamples = plainExamples;
    }

    public static FirstValueGroup from(List<PlainExample> plainExamples) {
        return new FirstValueGroup(Collections.unmodifiableList(plainExamples));
    }

    public List<PlainExample> plainExamples() {
        return plainExamples;
    }

    public List<PlainExample> by(MathOperator mathOperator) {
        return plainExamples.stream().filter(pe -> pe.mathOperator() == mathOperator).collect(Collectors.toList());
    }
}
