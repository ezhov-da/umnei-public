package ru.ezhov.umnei.mathematics.infrastructure;

import org.springframework.stereotype.Service;
import ru.ezhov.mathematics.engine.example.domain.model.FirstValue;
import ru.ezhov.mathematics.engine.example.domain.model.FirstValueGroup;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExampleGroupRepository;
import ru.ezhov.mathematics.engine.example.domain.model.ResultGroup;
import ru.ezhov.mathematics.engine.example.domain.model.ResultGroupRepositoryException;
import ru.ezhov.mathematics.engine.example.domain.model.ResultValue;
import ru.ezhov.mathematics.engine.example.domain.model.SecondValue;
import ru.ezhov.mathematics.engine.example.domain.model.SecondValueGroup;
import ru.ezhov.mathematics.engine.example.infrastructure.PlainExampleGroupRepositoryFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class PlainExampleGroupRepositoryImpl implements PlainExampleGroupRepository {
    private PlainExampleGroupRepository plainExampleGroupRepository = null;

    private void init() throws ResultGroupRepositoryException {
        try (InputStream inputStream = this.getClass().getResourceAsStream("/plain-example-store-0-1000.csv")) {
            plainExampleGroupRepository = PlainExampleGroupRepositoryFactory.csv(inputStream);
        } catch (IOException e) {
            throw new ResultGroupRepositoryException("Не удалось найти файл 'plain-example-store-0-1000.csv' в ресурсах", e);
        }
    }

    @Override
    public Optional<FirstValueGroup> by(FirstValue value) throws ResultGroupRepositoryException {
        if (plainExampleGroupRepository == null) {
            init();
        }
        return plainExampleGroupRepository.by(value);
    }

    @Override
    public Optional<SecondValueGroup> by(SecondValue value) throws ResultGroupRepositoryException {
        if (plainExampleGroupRepository == null) {
            init();
        }
        return plainExampleGroupRepository.by(value);
    }

    @Override
    public Optional<ResultGroup> by(ResultValue result) throws ResultGroupRepositoryException {
        if (plainExampleGroupRepository == null) {
            init();
        }
        return plainExampleGroupRepository.by(result);
    }
}
