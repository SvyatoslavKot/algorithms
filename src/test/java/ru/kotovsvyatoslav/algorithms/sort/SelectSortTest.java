package ru.kotovsvyatoslav.algorithms.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectSortTest {

    private SelectSort selectSort = new SelectSort();

    @Test
    public void testSort() {
        Integer[] arr = new Integer[]{12,13,45,43,11,2,34,21,2};
        selectSort.sort(arr);
    }
}