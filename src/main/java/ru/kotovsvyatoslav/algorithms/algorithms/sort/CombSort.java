package ru.kotovsvyatoslav.algorithms.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.AbstractSort;

public class CombSort extends AbstractSort {

    @Override
    public Integer[] sort(Integer[] array) {
        int gap = array.length;

        boolean isSorted = false;
        while (!isSorted || gap != 1) {

            if (gap >1 ){
                gap = gap * 10 / 13;
            }else {
                gap = 1;
            }

            isSorted = true;
            setMessageProduce("");

            for (int i = gap; i < array.length; i++) {
                if (array[i] < array[i - gap]) {
                    int t = array[i];
                    array[i] = array[i - gap];
                    array[i - gap] = t;
                    isSorted = false;
                }
            }
            setMessageProduce(arrayToString(array));
            printMsg(getMessageProduce());

        }
        return array;
    }
}
