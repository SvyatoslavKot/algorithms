package ru.kotovsvyatoslav.algorithms.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.AbstractSort;

public class InsertionSort extends AbstractSort {

    @Override
    public Integer[] sort(Integer[] array) {
        for (int i = 1; i < array.length; i ++) {
            int currunt = array[i];
            int j = i;
            while (j>0 && array[j - 1] > currunt) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = currunt;
        }
        printMsg(arrayToString(array));
        return array;
    }
}
