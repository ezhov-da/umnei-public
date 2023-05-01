package ru.ezhov.mathematics.engine.example.domain.model;

import java.util.Optional;

public interface PlainExampleGroupRepository {
    Optional<FirstValueGroup> by(FirstValue value) throws ResultGroupRepositoryException;

    Optional<SecondValueGroup> by(SecondValue value) throws ResultGroupRepositoryException;

    Optional<ResultGroup> by(ResultValue result) throws ResultGroupRepositoryException;
}
