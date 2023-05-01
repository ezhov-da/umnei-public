package ru.ezhov.mathematics.engine.task.domain;

import ru.ezhov.mathematics.engine.example.domain.model.FirstValue;
import ru.ezhov.mathematics.engine.example.domain.model.FirstValueGroup;
import ru.ezhov.mathematics.engine.example.domain.model.MathOperator;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExample;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExampleGroupRepository;
import ru.ezhov.mathematics.engine.example.domain.model.ResultGroupRepositoryException;
import ru.ezhov.mathematics.engine.example.domain.model.SecondValue;
import ru.ezhov.mathematics.engine.example.domain.model.SecondValueGroup;
import ru.ezhov.mathematics.engine.task.domain.model.MaxValue;
import ru.ezhov.mathematics.engine.task.domain.model.MinValue;
import ru.ezhov.mathematics.engine.task.domain.model.Tasks;
import ru.ezhov.mathematics.engine.task.domain.model.TypeExercise;
import ru.ezhov.mathematics.engine.task.domain.model.UnsupportedMathOperatorException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TaskService {
    private final PlainExampleGroupRepository plainExampleGroupRepository;

    public TaskService(PlainExampleGroupRepository plainExampleGroupRepository) {
        this.plainExampleGroupRepository = plainExampleGroupRepository;
    }

    public Tasks create(TypeExercise typeExercise, List<MathOperator> mathOperators, MinValue minValue, MaxValue maxValue) throws TaskServiceException {
        try {

            Set<PlainExample> iterate = new HashSet<>();

            for (int i = minValue.value(); i <= maxValue.value(); i++) {
                final Optional<FirstValueGroup> firstValueGroupOptional = plainExampleGroupRepository.by(FirstValue.create(i));

                if (firstValueGroupOptional.isPresent()) {
                    final FirstValueGroup firstValueGroup = firstValueGroupOptional.get();
                    final List<PlainExample> plainExamples = firstValueGroup.plainExamples();
                    for (PlainExample pe : plainExamples) {
                        if (pe.first().value() >= minValue.value() &&
                                pe.first().value() <= maxValue.value() &&
                                pe.second().value() >= minValue.value() &&
                                pe.second().value() <= maxValue.value() &&
                                mathOperators.contains(pe.mathOperator())) {
                            iterate.add(pe);
                        }
                    }


                }

                final Optional<SecondValueGroup> secondValueOptional = plainExampleGroupRepository.by(SecondValue.create(i));

                if (secondValueOptional.isPresent()) {
                    final SecondValueGroup secondValue = secondValueOptional.get();
                    final List<PlainExample> plainExamples = secondValue.plainExamples();
                    for (PlainExample pe : plainExamples) {
                        if (pe.first().value() >= minValue.value() &&
                                pe.first().value() <= maxValue.value() &&
                                pe.second().value() >= minValue.value() &&
                                pe.second().value() <= maxValue.value() &&
                                mathOperators.contains(pe.mathOperator())) {
                            iterate.add(pe);
                        }
                    }
                }
            }

            return Tasks.create(typeExercise, iterate);
        } catch (ResultGroupRepositoryException | UnsupportedMathOperatorException e) {
            throw new TaskServiceException("Ошибка генерации примеров для типа '" + typeExercise + "' и максимального значения '" + maxValue + "'", e);
        }
    }
}
