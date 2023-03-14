package ru.kotovsvyatoslav.algorithms.parser;

import org.junit.jupiter.api.Test;
import ru.kotovsvyatoslav.algorithms.util.parser.StringToIntegerArray;

import static org.junit.jupiter.api.Assertions.*;

class StringToIntegerArrayTest {

    StringToIntegerArray stringToIntegerArray = new StringToIntegerArray();

    @Test
    void stringToArray() {

        Integer[] integers = stringToIntegerArray.stringToArray("1,2,3,4,5");
        assertTrue(integers.length == 5);

    }

}