package ru.kotovsvyatoslav.algorithms.sort;

import org.junit.jupiter.api.Test;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.MergeSort;

class MergeSortTest {
    private MergeSort mergeSort = new MergeSort();

    @Test
    public void mergeSortTest() {
        Integer[] array = new Integer[]{12,13,45,43,11,2,34,21,2,0};
        mergeSort.sort(array);
    }
}