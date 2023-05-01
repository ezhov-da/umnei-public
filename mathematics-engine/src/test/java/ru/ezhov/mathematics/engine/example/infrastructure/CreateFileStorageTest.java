package ru.ezhov.mathematics.engine.example.infrastructure;

import org.junit.Ignore;
import org.junit.Test;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExample;
import ru.ezhov.mathematics.engine.example.domain.model.ResultValue;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class CreateFileStorageTest {
    @Test
    @Ignore("Только для генерации")
    public void createStorage() throws Exception {
        MathematicsGenerator mathematicsGenerator = new MathematicsGenerator();
        Map<ResultValue, List<PlainExample>> generate = mathematicsGenerator.generate(0, 1000);

        try (FileWriter fileWriter = new FileWriter(new File("plain-example-store-0-1000.csv"), false)) {
            for (Map.Entry<ResultValue, List<PlainExample>> entry : generate.entrySet()) {
                final List<PlainExample> list = entry.getValue();
                for (PlainExample plainExample : list) {
                    fileWriter.append(
                            String.format(
                                    "%s;%s;%s;%s\n",
                                    plainExample.first().value(),
                                    plainExample.mathOperator().symbol(),
                                    plainExample.second().value(),
                                    plainExample.result().value()
                            )
                    );
                }
            }
        }
    }
}
