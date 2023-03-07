package ru.kotovsvyatoslav.algorithms.util.messageSender;

public class ConsoleMessageSender implements MessageSender{

    @Override
    public void messageSend(String msg) {
        System.out.println(msg);
    }
}
