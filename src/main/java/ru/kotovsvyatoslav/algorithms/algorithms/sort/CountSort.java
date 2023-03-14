package ru.kotovsvyatoslav.algorithms.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.AbstractSort;

public class CountSort extends AbstractSort {

    private final int MAX_VALUE = 100;

    @Override
    public Integer[] sort(Integer[] array) {
        int[] count = new int[MAX_VALUE];
        for (int i = 0; i < array.length; i++) {
            count[array[i]] = count[array[1]] + 1;
        }

        int arrayIndex = 0 ;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                array[arrayIndex] = i;
                arrayIndex++;
            }
        }
        printMsg(arrayToString(array));
        return array;
    }
}
