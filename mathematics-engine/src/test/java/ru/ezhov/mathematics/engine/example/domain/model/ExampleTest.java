package ru.ezhov.mathematics.engine.example.domain.model;

import org.junit.Test;
import ru.ezhov.mathematics.engine.example.domain.model.value.AdditionOperatorValue;
import ru.ezhov.mathematics.engine.example.domain.model.value.IntValue;
import ru.ezhov.mathematics.engine.example.domain.model.value.ResultValue;

import java.util.List;

public class ExampleTest {
    @Test
    public void example() {
        Example exampleBasic = Example.create(IntValue.create(5), AdditionOperatorValue.create(), IntValue.create(5), ResultValue.create(10));

        System.out.println(exampleBasic.asString());

        Example exampleAdd = Example.create(IntValue.create(2), AdditionOperatorValue.create(), IntValue.create(3), ResultValue.create(5));

        System.out.println(exampleAdd.asString());

        Example exampleAdd2 = Example.create(IntValue.create(2), AdditionOperatorValue.create(), IntValue.create(1), ResultValue.create(3));

        List<Example.AvailableValue> availableValues = exampleAdd.availableValues();
        if (availableValues.size() > 0) {
            Example.AvailableValue availableValue = availableValues.get(1);

            Example result = exampleAdd.combine(availableValue, exampleAdd2);

            System.out.println(result.asString());
//            System.out.println(exampleBasic.combine(result).asString());
        }
    }
}