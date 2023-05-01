package ru.ezhov.mathematics.engine.example.infrastructure;

import ru.ezhov.mathematics.engine.example.domain.model.PlainExampleGroupRepository;
import ru.ezhov.mathematics.engine.example.domain.model.ResultGroupRepositoryException;

import java.io.InputStream;

public abstract class PlainExampleGroupRepositoryFactory {
    public static PlainExampleGroupRepository csv(InputStream inputStream) throws ResultGroupRepositoryException {
        return new CsvPlainExampleGroupRepository(inputStream);
    }
}
