package ru.ezhov.mathematics.engine.example.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SecondValueGroup {
    private final List<PlainExample> plainExamples;

    public SecondValueGroup(List<PlainExample> plainExamples) {
        this.plainExamples = plainExamples;
    }

    public static SecondValueGroup from(List<PlainExample> plainExamples) {
        return new SecondValueGroup(Collections.unmodifiableList(plainExamples));
    }

    public List<PlainExample> plainExamples() {
        return plainExamples;
    }

    public List<PlainExample> by(MathOperator mathOperator) {
        return plainExamples.stream().filter(pe -> pe.mathOperator()== mathOperator).collect(Collectors.toList());
    }
}
