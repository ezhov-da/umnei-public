package ru.ezhov.mathematics.engine.example.domain.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PlainExampleTest {
    @Test
    public void create() {
        PlainExample plainExampleAddition = PlainExample.create(FirstValue.create(2), MathOperator.ADDITION, SecondValue.create(3), ResultValue.create(5));
        assertEquals("2 + 3 = 5", plainExampleAddition.asString());

        assertEquals("5 - 3 = 2", plainExampleAddition.resultAsFirstValue().asString());
        assertEquals("5 - 2 = 3", plainExampleAddition.resultAsSecondValue().asString());

        PlainExample plainExampleSubtraction = PlainExample.create(FirstValue.create(5), MathOperator.SUBTRACTION, SecondValue.create(3), ResultValue.create(2));
        assertEquals("5 - 3 = 2", plainExampleSubtraction.asString());

        assertEquals("2 + 3 = 5", plainExampleSubtraction.resultAsFirstValue().asString());
        assertEquals("5 - 2 = 3", plainExampleSubtraction.resultAsSecondValue().asString());

        PlainExample plainExampleMultiplication = PlainExample.create(FirstValue.create(2), MathOperator.MULTIPLICATION, SecondValue.create(3), ResultValue.create(6));
        assertEquals("2 * 3 = 6", plainExampleMultiplication.asString());

        assertEquals("6 : 3 = 2", plainExampleMultiplication.resultAsFirstValue().asString());
        assertEquals("6 : 2 = 3", plainExampleMultiplication.resultAsSecondValue().asString());

        PlainExample plainExampleDivision = PlainExample.create(FirstValue.create(6), MathOperator.DIVISION, SecondValue.create(3), ResultValue.create(2));
        assertEquals("6 : 3 = 2", plainExampleDivision.asString());

        assertEquals("2 * 3 = 6", plainExampleDivision.resultAsFirstValue().asString());
        assertEquals("6 : 2 = 3", plainExampleDivision.resultAsSecondValue().asString());
    }
}