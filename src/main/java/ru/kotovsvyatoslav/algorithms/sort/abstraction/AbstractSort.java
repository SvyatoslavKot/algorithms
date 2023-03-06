package ru.kotovsvyatoslav.algorithms.sort.abstraction;

import ru.kotovsvyatoslav.algorithms.util.MessageSender;

public abstract class AbstractSort implements Sortable {

    public String  messageProduce = new String();
    private MessageSender messageSender = new MessageSender();


    public void printMsg(String msg) {
        messageSender.messageSend(msg);
    }
    public String arrayToString ( Integer[] integers) {
        String s = "";
        for (Integer i : integers) {
            s = s + i.toString() + ", ";
        }
        return s;
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
}
