package ru.ezhov.mathematics.engine.example.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.mathematics.engine.example.domain.model.*;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class CsvPlainExampleGroupRepository implements PlainExampleGroupRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CsvPlainExampleGroupRepository.class);
    private final Map<Integer, List<PlainExample>> firstMap = new ConcurrentHashMap<>();
    private final Map<Integer, List<PlainExample>> secondMap = new ConcurrentHashMap<>();
    private final Map<Integer, List<PlainExample>> resultMap = new ConcurrentHashMap<>();

    public CsvPlainExampleGroupRepository(InputStream inputStream) throws ResultGroupRepositoryException {
        this.init(inputStream);
    }

    private void init(InputStream inputStream) throws ResultGroupRepositoryException {
        long startTime = System.currentTimeMillis();
        LOG.info("method=init action=\"начато наполнение хранилищиа\"");
        final Scanner scanner = new Scanner(inputStream).useDelimiter("\n");
        long counter = 0;
        while (scanner.hasNextLine()) {
            final String nextLine = scanner.nextLine();
            final String[] split = nextLine.split(";");

            FirstValue firstValue = FirstValue.create(Integer.parseInt(split[0]));
            SecondValue secondValue = SecondValue.create(Integer.parseInt(split[2]));
            final ResultValue resultValue = ResultValue.create(split[3]);

            final PlainExample plainExample = PlainExample.create(
                    firstValue,
                    MathOperator.by(split[1]),
                    secondValue,
                    ResultValue.create(Integer.parseInt(split[3]))
            );


            List<PlainExample> firstPlainExamples = firstMap.computeIfAbsent(firstValue.value(), k -> new ArrayList<>());
            List<PlainExample> secondPlainExamples = secondMap.computeIfAbsent(secondValue.value(), k -> new ArrayList<>());
            List<PlainExample> resultPlainExamples = resultMap.computeIfAbsent(resultValue.value(), k -> new ArrayList<>());

            firstPlainExamples.add(plainExample);
            secondPlainExamples.add(plainExample);
            resultPlainExamples.add(plainExample);

            resultMap.put(firstValue.value(), firstPlainExamples);
            resultMap.put(secondValue.value(), secondPlainExamples);
            resultMap.put(resultValue.value(), resultPlainExamples);

            counter++;

            if (counter % 30000 == 0) {
                LOG.info("method=init \"обработано записей\"={}", counter);
            }
        }
        LOG.info(
                "method=init action=\"завершено наполнение хранилища\" \"уникальных значений\"={} \"время наполнения\"={}ms",
                resultMap.size(),
                System.currentTimeMillis() - startTime
        );
    }

    @Override
    public Optional<FirstValueGroup> by(FirstValue value) throws ResultGroupRepositoryException {
        final List<PlainExample> plainExamples = firstMap.get(value.value());
        if (plainExamples == null) {
            return Optional.empty();
        } else {
            return Optional.of(FirstValueGroup.from(plainExamples));
        }
    }

    @Override
    public Optional<SecondValueGroup> by(SecondValue value) throws ResultGroupRepositoryException {
        final List<PlainExample> plainExamples = secondMap.get(value.value());
        if (plainExamples == null) {
            return Optional.empty();
        } else {
            return Optional.of(SecondValueGroup.from(plainExamples));
        }
    }

    @Override
    public Optional<ResultGroup> by(ResultValue result) throws ResultGroupRepositoryException {
        final List<PlainExample> plainExamples = resultMap.get(result.value());
        if (plainExamples == null) {
            return Optional.empty();
        } else {
            return Optional.of(ResultGroup.from(plainExamples));
        }
    }
}
