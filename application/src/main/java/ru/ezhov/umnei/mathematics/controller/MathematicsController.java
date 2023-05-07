package ru.ezhov.umnei.mathematics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ezhov.mathematics.engine.task.domain.model.HiddenValueNumber;
import ru.ezhov.mathematics.engine.task.domain.model.Task;
import ru.ezhov.mathematics.engine.task.domain.model.Tasks;
import ru.ezhov.umnei.mathematics.application.MathematicsApplication;
import ru.ezhov.umnei.mathematics.application.MathematicsApplicationException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Random;

@RestController
public class MathematicsController {
    private static final Logger LOG = LoggerFactory.getLogger(MathematicsController.class);

    private MathematicsApplication mathematicsApplication;

    public MathematicsController(MathematicsApplication mathematicsApplication) {
        this.mathematicsApplication = mathematicsApplication;
    }

    @GetMapping(produces = "application/json")
    @RequestMapping("/tasks/generate")
    public String tasks(@NotNull @RequestParam String type,
                        @NotNull @RequestParam String actions,
                        @NotNull @RequestParam int countTasks,
                        @NotNull @RequestParam int from,
                        @NotNull @RequestParam int to
    ) {
        LOG.debug(
                "method=tasks action=\"запрошен список задач\" type={} actions={} countTasks={} from={} to={}",
                type, actions, countTasks, from, to
        );

        try {
            Tasks tasks = mathematicsApplication.generate(type, actions, from, to);

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            List<Task> all = tasks.all();

            for (int i = 0; i < all.size(); i++) {

                if ((i + 1) > countTasks) {
                    break;
                }

                Task task = all.get(new Random().nextInt(all.size()));
                HiddenValueNumber hiddenValueNumber = task.hiddenValueNumber();

                arrayBuilder.add(
                        Json.createObjectBuilder()
                                .add("first", hiddenValueNumber == HiddenValueNumber.FIRST ? "?" : task.firstValue().value() + "")
                                .add("second", hiddenValueNumber == HiddenValueNumber.SECOND ? "?" : task.secondValue().value() + "")
                                .add("third", hiddenValueNumber == HiddenValueNumber.THIRD ? "?" : task.thirdValue().value() + "")
                                .add("operator", task.mathOperator().symbol())
                                .add("answer", (hiddenValueNumber == HiddenValueNumber.FIRST ? task.firstValue().value() :
                                        hiddenValueNumber == HiddenValueNumber.SECOND ? task.secondValue().value() : task.thirdValue().value()) + ""
                                ).build()
                );
                if (i > 100) {
                    break;
                }
            }

            JsonObject jsonObject = Json.createObjectBuilder().add(
                    "tasks", arrayBuilder.build()
            ).build();
            return jsonObject.toString();
        } catch (MathematicsApplicationException e) {
            throw new RuntimeException("бла бла");
        }
    }
}
