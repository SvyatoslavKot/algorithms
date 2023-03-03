package ru.kotovsvyatoslav.algorithms.sort;

public class SelectSort {

    public void sort(Integer[] array) {
        String print;
        for (int step = 0; step < array.length; step ++) {
            int minValue = array[step];
            int indexMin = step;

            for (int i = step; i <array.length; i ++){
                if (array[i] < minValue) {
                    minValue = array[i];
                    indexMin = i;
                }
            }
            int tmp = array[step];
            array[step] = array[indexMin];
            array[indexMin] = tmp;
            print ="";
            for (int a : array) {
                print = print + a + " ";

            }
            System.out.println(print);
        }
    }

}
