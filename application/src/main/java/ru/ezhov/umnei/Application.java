package ru.ezhov.umnei;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ezhov.mathematics.engine.example.domain.model.FirstValue;
import ru.ezhov.mathematics.engine.example.domain.model.PlainExampleGroupRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final PlainExampleGroupRepository plainExampleGroupRepository;

    public Application(PlainExampleGroupRepository plainExampleGroupRepository) {
        this.plainExampleGroupRepository = plainExampleGroupRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        plainExampleGroupRepository.by(FirstValue.create(10));
    }
}


