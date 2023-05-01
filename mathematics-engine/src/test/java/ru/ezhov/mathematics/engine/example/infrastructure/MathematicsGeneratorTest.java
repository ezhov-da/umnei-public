package ru.ezhov.mathematics.engine.example.infrastructure;

import org.junit.Test;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExample;
import ru.ezhov.mathematics.engine.example.domain.model.ResultValue;
import ru.ezhov.mathematics.engine.example.domain.model.Value;

import java.util.List;
import java.util.Map;

public class MathematicsGeneratorTest {
    @Test
    public void test() throws Exception {
        MathematicsGenerator mathematicsGenerator = new MathematicsGenerator();
        Map<ResultValue, List<PlainExample>> generate = mathematicsGenerator.generate(10, 20);

        System.out.println(generate.size());
    }
}