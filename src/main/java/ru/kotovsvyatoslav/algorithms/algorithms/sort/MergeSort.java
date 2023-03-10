package ru.kotovsvyatoslav.algorithms.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.AbstractSort;

public class MergeSort extends AbstractSort {

    public Integer[] sort (Integer[] array) {
        Integer[] tmp;
        Integer[] currentSrc = array;
        Integer[] currentDest = new Integer[array.length];

        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                merge(currentSrc, i, currentSrc, i + size, currentDest, i, size);
            }
            tmp = currentSrc;
            currentSrc = currentDest;
            currentDest = tmp;

            size = size * 2;
            printMsg(arrayToString(currentSrc));
        }

        return currentSrc;
    }


    private void merge ( Integer[] srcOne, int srcOneStart, Integer[] srcTwo, int srcTwoStart, Integer[] dest, int destStart, int size) {
        int index1 = srcOneStart;
        int index2 = srcTwoStart;

        int srcOneEnd = Math.min(srcOneStart + size, srcOne.length);
        int srcTwoEnd = Math.min(srcTwoStart + size, srcTwo.length);

        int iterationCount = srcOneEnd - srcOneStart + srcTwoEnd - srcTwoStart;

        for (int i = destStart; i < destStart + iterationCount; i++) {
            if (index1 < srcOneEnd && (index2 >= srcTwoEnd || srcOne[index1] < srcTwo[index2])) {
                dest[i] = srcOne[index1];
                index1++;
            } else {
                dest[i] = srcTwo[index2];
                index2++;
            }
        }
    }

}
