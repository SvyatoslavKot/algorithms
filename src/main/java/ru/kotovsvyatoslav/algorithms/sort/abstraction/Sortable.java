package ru.kotovsvyatoslav.algorithms.sort.abstraction;

import ru.kotovsvyatoslav.algorithms.util.MessageSender;

public interface Sortable {

    Integer[] sort(Integer[] array);

    default void printMsg(String msg) {
        System.out.println(msg);
    };

    default String arrayToString ( Integer[] integers) {
        String s = "";
        for (Integer i : integers) {
            s = s + i.toString() + ", ";
        }
        return s;
    }

    default void setMessageSender(MessageSender messageSender){

    };
}
