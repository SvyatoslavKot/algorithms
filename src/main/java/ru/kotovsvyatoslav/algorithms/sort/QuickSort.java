package ru.kotovsvyatoslav.algorithms.sort;


import ru.kotovsvyatoslav.algorithms.sort.abstraction.AbstractSort;

public class QuickSort extends AbstractSort {

    @Override
    public Integer[] sort(Integer[] integers) {
        printMsg("Start Sort Quick");
        Integer[] sortArray =  quickSort(integers,0, integers.length-1);
        printMsg("result => " + arrayToString(sortArray));
        printMsg("End Sort Quick");
        return sortArray;
    }



    private  Integer[] quickSort (Integer[] arr, int from, int to) {
        if ( from < to ) {
            int divideIndex = partition(arr, from,to);
            printMsg("Divide index -> " + divideIndex);
            quickSort(arr, from, divideIndex-1);
            quickSort(arr, divideIndex, to);
        }
        return arr;
    }

    private int partition(Integer[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[from];
        while (leftIndex <= rightIndex) {
            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }
            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {

                printMsg("Partition  method -> indexFrom ->"+ from + ", indexTo -> "+ to + "array -> " +  arrayToString(arr));
                swap(arr,rightIndex,leftIndex);
                printMsg("Partition  method after swap  array -> " +  arrayToString(arr));

                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(Integer[] arr, int rightIndex, int leftIndex) {
        int tmp = arr[rightIndex];
        arr[rightIndex] = arr[leftIndex];
        arr[leftIndex] = tmp;

    }

}
