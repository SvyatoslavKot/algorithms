package ru.kotovsvyatoslav.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.sort.abstraction.Sortable;
import ru.kotovsvyatoslav.algorithms.util.MessageSender;

public class SelectSort implements Sortable {

    private MessageSender messageSender;

    public SelectSort() {
        this.messageSender = new MessageSender();
    }

    public Integer[] sort(Integer[] array) {
        String print;
        printMsg("SelectSort start");
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

            print = arrayToString(array);
            printMsg(print);
        }
        printMsg("SelectSort End");
        return array;
    }

    @Override
    public void setMessageSender(MessageSender messageSender) {
            this.messageSender = messageSender;
    }

    @Override
    public void printMsg(String msg) {
        messageSender.messageSend(msg);
    }
}
