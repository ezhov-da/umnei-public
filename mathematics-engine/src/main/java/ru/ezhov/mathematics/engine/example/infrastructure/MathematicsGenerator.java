package ru.ezhov.mathematics.engine.example.infrastructure;

import ru.ezhov.mathematics.engine.example.domain.model.FirstValue;
import ru.ezhov.mathematics.engine.example.domain.model.MathOperator;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExample;
import ru.ezhov.mathematics.engine.example.domain.model.ResultValue;
import ru.ezhov.mathematics.engine.example.domain.model.SecondValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//только для внутреннего использования
class MathematicsGenerator {

    public Map<ResultValue, List<PlainExample>> generate(int minValue, int maxValue) {
        Map<ResultValue, List<PlainExample>> map = new HashMap<>();
        for (int i = minValue; i <= maxValue; i++) {
            for (int c = minValue; c <= maxValue; c++) {
                int additionRaw = i + c;
                int subtractionRaw = i - c;
                int multiplicationRaw = i * c;

                List<PlainExample> examples = map.get(ResultValue.create(additionRaw));
                if (examples == null) {
                    examples = new ArrayList<>();
                    map.put(ResultValue.create(additionRaw), examples);
                }
                examples.add(PlainExample.create(FirstValue.create(i), MathOperator.ADDITION, SecondValue.create(c), ResultValue.create(additionRaw)));

                if (subtractionRaw >= 0) {
                    examples = map.get(ResultValue.create(subtractionRaw));
                    if (examples == null) {
                        examples = new ArrayList<>();
                        map.put(ResultValue.create(subtractionRaw), examples);
                    }
                    examples.add(PlainExample.create(FirstValue.create(i), MathOperator.SUBTRACTION, SecondValue.create(c), ResultValue.create(subtractionRaw)));
                }

                if (multiplicationRaw >= 0) {
                    examples = map.get(ResultValue.create(multiplicationRaw));
                    if (examples == null) {
                        examples = new ArrayList<>();
                        map.put(ResultValue.create(multiplicationRaw), examples);
                    }
                    examples.add(PlainExample.create(FirstValue.create(i), MathOperator.MULTIPLICATION, SecondValue.create(c), ResultValue.create(multiplicationRaw)));
                }

                if (c != 0 && (i % c) == 0 && i / c >= 0) {
                    examples = map.get(ResultValue.create(i / c));
                    if (examples == null) {
                        examples = new ArrayList<>();
                        map.put(ResultValue.create(i / c), examples);
                    }
                    examples.add(PlainExample.create(FirstValue.create(i), MathOperator.DIVISION, SecondValue.create(c), ResultValue.create(i / c)));
                }
            }
        }
        return map;
    }
}
