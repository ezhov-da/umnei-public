package ru.ezhov.umnei.mathematics.imagegenerator;

import org.springframework.stereotype.Service;
import ru.ezhov.imagegenerator.api.ImageGenerator;
import ru.ezhov.imagegenerator.api.ImageGeneratorException;
import ru.ezhov.imagegenerator.api.ImageGeneratorFactory;
import ru.ezhov.mathematics.engine.task.domain.model.HiddenValueNumber;
import ru.ezhov.mathematics.engine.task.domain.model.Task;
import ru.ezhov.mathematics.engine.task.domain.model.Tasks;
import ru.ezhov.umnei.mathematics.application.MathematicsApplication;
import ru.ezhov.umnei.mathematics.application.MathematicsApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ImageGeneratorApplicationService {
    private final MathematicsApplication mathematicsApplication;

    public ImageGeneratorApplicationService(MathematicsApplication mathematicsApplication) {
        this.mathematicsApplication = mathematicsApplication;
    }

    public byte[] createImage(String type,
                              String actions,
                              int from,
                              int to) throws ImageGeneratorApplicationServiceException {
        try {
            Tasks tasks = mathematicsApplication.generate(type, actions, from, to);
            List<Task> all = tasks.all();
            List<Task> randomTask = new ArrayList<>();
            for (int i = 0; i < all.size(); i++) {
                randomTask.add(all.get(new Random().nextInt(all.size())));
            }

            List<String> values = randomTask.stream().map(t -> {
                final HiddenValueNumber hiddenValueNumber = t.hiddenValueNumber();
                return String.format("%s %s %s = %s",
                        hiddenValueNumber == HiddenValueNumber.FIRST ? "__" : t.firstValue().value(),
                        t.mathOperator().symbol(),
                        hiddenValueNumber == HiddenValueNumber.SECOND ? "__" : t.secondValue().value(),
                        hiddenValueNumber == HiddenValueNumber.FIRST ? t.firstValue().value() :
                                hiddenValueNumber == HiddenValueNumber.SECOND ? t.secondValue().value() : t.thirdValue().value()
                );
            }).collect(Collectors.toList());

            final ImageGenerator imageGenerator = ImageGeneratorFactory.getInstance("Примеры по математике", "умней.рус", values);

            return imageGenerator.generate();
        } catch (MathematicsApplicationException e) {
            throw new ImageGeneratorApplicationServiceException("Ошибка генерации примеров", e);
        } catch (ImageGeneratorException e) {
            throw new ImageGeneratorApplicationServiceException("Ошибка создания изображения", e);
        }

    }
}
