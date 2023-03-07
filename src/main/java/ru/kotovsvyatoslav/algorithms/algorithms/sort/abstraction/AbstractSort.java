package ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction;

import ru.kotovsvyatoslav.algorithms.util.messageSender.ConsoleMessageSender;
import ru.kotovsvyatoslav.algorithms.util.messageSender.MessageSender;

public abstract class AbstractSort implements Sortable {

    private String  messageProduce = new String();
    private MessageSender messageSender = new ConsoleMessageSender();


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
    public String getMessageProduce() {
        return messageProduce;
    }
    public void setMessageProduce(String messageProduce) {
        this.messageProduce = messageProduce;
    }
}
