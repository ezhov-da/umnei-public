package ru.ezhov.umnei.mathematics.application;

import org.springframework.stereotype.Service;
import ru.ezhov.mathematics.engine.example.domain.model.MathOperator;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExampleGroupRepository;
import ru.ezhov.mathematics.engine.task.domain.TaskService;
import ru.ezhov.mathematics.engine.task.domain.TaskServiceException;
import ru.ezhov.mathematics.engine.task.domain.model.MaxValue;
import ru.ezhov.mathematics.engine.task.domain.model.MinValue;
import ru.ezhov.mathematics.engine.task.domain.model.Tasks;
import ru.ezhov.mathematics.engine.task.domain.model.TypeExercise;

import java.util.ArrayList;
import java.util.List;

@Service
public class MathematicsApplication {

    private final PlainExampleGroupRepository plainExampleGroupRepository;

    public MathematicsApplication(PlainExampleGroupRepository plainExampleGroupRepository) {
        this.plainExampleGroupRepository = plainExampleGroupRepository;
    }

    public Tasks generate(String type, String actions, int from, int to) throws MathematicsApplicationException {
        //            type=numberEscaped&actions=addition,subtraction,multiplication,division

        TypeExercise typeExercise;
        if ("numberEscaped".equals(type)) {
            typeExercise = TypeExercise.NUMBER_ESCAPED;
        } else if ("mathExercise".equals(type)) {
            typeExercise = TypeExercise.MATH_EXERCISE;
        } else {
            throw new MathematicsApplicationException("Неподдерживаемый тип '" + type + "'");
        }

        List<MathOperator> operators = new ArrayList<>();
        String[] splitActions = actions.split(",");
        for (String action : splitActions) {
            switch (action) {
                case "addition":
                    operators.add(MathOperator.ADDITION);
                    break;
                case "subtraction":
                    operators.add(MathOperator.SUBTRACTION);
                    break;
                case "multiplication":
                    operators.add(MathOperator.MULTIPLICATION);
                    break;
                case "division":
                    operators.add(MathOperator.DIVISION);
                    break;
            }
        }

        TaskService taskService = new TaskService(plainExampleGroupRepository);
        try {
            return taskService.create(
                    typeExercise,
                    operators,
                    MinValue.create(from),
                    MaxValue.create(Math.max(1, to))
            );
        } catch (TaskServiceException e) {
            throw new MathematicsApplicationException("Ошибка при генерации данных", e);
        }
    }
}
