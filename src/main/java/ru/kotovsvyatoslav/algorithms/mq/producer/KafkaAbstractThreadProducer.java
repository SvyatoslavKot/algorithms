package ru.kotovsvyatoslav.algorithms.mq.producer;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class KafkaAbstractThreadProducer extends Thread {

    public Queue<String> msgQueue = new ArrayDeque<>();
    private boolean sorting;
    private boolean producing;
    public String topic;
    public KafkaProducer kafkaProducer;

    public KafkaAbstractThreadProducer (KafkaProducer kafkaProducer, String topic){
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
    }

    abstract void messageProd();

    @Override
    public void run() {
        sorting = true;
        producing = true;
        while (producing){
            if (msgQueue.isEmpty() && sorting){
                //если очередь сообщений пуста, но флаг сортировка не закончена ставим поток в ожидание
                try {
                    synchronized (this){
                        this.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (msgQueue.isEmpty() && !sorting){
                // если очередь сообщений пуста и сортировка закончена прекращаем отправления сообщений, выходим из цикла
                producing = false;
            }else {
                // если первые два условия не выполнены, отправляем сообщения
                messageProd();
            }
        }
    }

    public void setSorting(boolean sorting) {
        this.sorting = sorting;
    }
    public void addMessage (String msg) {
        this.msgQueue.add(msg);
    }
}
