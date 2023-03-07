package ru.kotovsvyatoslav.algorithms.mq.producer;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class MQAbstractThreadProducer extends Thread {

    private MQProducer producer;
    private String topic;

    private Queue<String> msgQueue = new ArrayDeque<>();
    private boolean working;
    private boolean producing;

    public MQAbstractThreadProducer(MQProducer producer, String topic){
        this.producer = producer;
        this.topic = topic;
    }



    @Override
    public void run() {
        working = true;
        producing = true;
        while (producing){
            if (msgQueue.isEmpty() && working){
                //если очередь сообщений пуста, но флаг сортировка не закончена ставим поток в ожидание
                try {
                    synchronized (this){
                        this.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (msgQueue.isEmpty() && !working){
                // если очередь сообщений пуста и сортировка закончена прекращаем отправления сообщений, выходим из цикла
                producing = false;
            }else {
                // если первые два условия не выполнены, отправляем сообщения
                messageProd();
            }
        }
    }

    abstract void messageProd();


    public void setWorking(boolean working) {
        this.working = working;
    }
    public void addMessage (String msg) {
        this.msgQueue.add(msg);
    }
    public String getTopic() {
        return topic;
    }
    public Queue<String> getMsgQueue() {
        return msgQueue;
    }
    public MQProducer getProducer() {
        return producer;
    }
}
