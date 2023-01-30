package ru.kotovsvyatoslav.algorithms.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringToIntegerArray {

    public Integer[] stringToArray (String string) {
        String[] stringArray =  string.split(",");
        Integer[] intArray = new Integer[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }
}
