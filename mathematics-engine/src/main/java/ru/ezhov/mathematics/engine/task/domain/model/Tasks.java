package ru.ezhov.mathematics.engine.task.domain.model;

import ru.ezhov.mathematics.engine.example.domain.model.PlainExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Tasks {
    private final List<Task> tasks = new ArrayList<>();

    private Tasks() {
    }

    public static Tasks create(TypeExercise typeExercise, Set<PlainExample> examples) throws UnsupportedMathOperatorException {
        Tasks tasks = new Tasks();

        switch (typeExercise) {
            case NUMBER_ESCAPED:
                tasks.createNumberEscaped(examples);
                break;
            case MATH_EXERCISE:
                tasks.createMathExercise(examples);
                break;
            default:
                throw new UnsupportedTypeExerciseException("Неподдерживаемый тип заданий '" + typeExercise + "'");
        }

        return tasks;

    }

    private void createNumberEscaped(Set<PlainExample> examples) throws UnsupportedMathOperatorException {
        for (PlainExample example : examples) {
            boolean isFirstValueHidden = 1 == new Random().nextInt(2) + 1;
            if (isFirstValueHidden) {
                tasks.add(Task.create(HiddenValueNumber.FIRST, example));
            } else {
                tasks.add(Task.create(HiddenValueNumber.SECOND, example));
            }
        }
    }

    private void createMathExercise(Set<PlainExample> examples) throws UnsupportedMathOperatorException {
        for (PlainExample example : examples) {
            tasks.add(Task.create(HiddenValueNumber.THIRD, example));

        }
    }

    public List<Task> all() {
        return tasks;
    }
}
