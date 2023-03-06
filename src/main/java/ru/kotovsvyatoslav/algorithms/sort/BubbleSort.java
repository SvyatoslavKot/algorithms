package ru.kotovsvyatoslav.algorithms.sort;

import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.AbstractSort;

@Component
public class BubbleSort extends AbstractSort {

    @Override
    public Integer[] sort(Integer[] array) {
        printMsg("BubbleSort Start");
        boolean isSorted = false;
        int indexI = 1;
        while (!isSorted) {
            isSorted = true;
            messageProduce = "";

            for (int i = indexI; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int t = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = t;
                    isSorted = false;
                }
            }
            messageProduce = arrayToString(array);
            printMsg(messageProduce);
        }
        printMsg("BubbleSort End");
        return array;
    }

}
