package ru.ezhov.mathematics.engine.example.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResultGroup {
    private final List<PlainExample> plainExamples;

    public ResultGroup(List<PlainExample> plainExamples) {
        this.plainExamples = plainExamples;
    }

    public static ResultGroup from(List<PlainExample> plainExamples) {
        return new ResultGroup(Collections.unmodifiableList(plainExamples));
    }

    public List<PlainExample> plainExamples() {
        return plainExamples;
    }

    public List<PlainExample> by(MathOperator mathOperator) {
        return plainExamples.stream().filter(pe -> pe.mathOperator() == mathOperator).collect(Collectors.toList());
    }
}
