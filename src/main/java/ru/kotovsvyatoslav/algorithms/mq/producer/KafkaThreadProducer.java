package ru.kotovsvyatoslav.algorithms.mq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class KafkaThreadProducer extends Thread{

    private Queue<String> msgQueue = new ArrayDeque<>();
    private String sessionId;
    private String topic;
    private boolean sorting;

    private KafkaProducer kafkaProducer;

    public KafkaThreadProducer(KafkaProducer kafkaProducer, String topic, String sessionId) {
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
        this.sessionId = sessionId;
    }

    @Override
    public void run() {
        System.out.println("producer start");
        System.out.println(sorting);
        sorting=true;
        boolean producing = true;
        while (producing){
            if (msgQueue.isEmpty() && sorting){
                try {
                    synchronized (this){
                        System.out.println("producer wait");
                        this.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (msgQueue.isEmpty() && !sorting){
                System.out.println("produce end");
                producing = false;
            }else {
                System.out.println("producer post");
                HashMap<String, String> msgMag = new HashMap<>();
                msgMag.put("sessionId", sessionId);
                msgMag.put("message", msgQueue.poll());

                kafkaProducer.produce(topic, msgMag);
            }

        }
        System.out.println("producer end");

    }
    public void setSorting(boolean sorting) {
        this.sorting = sorting;
    }
    public void addMessage (String msg) {
        this.msgQueue.add(msg);
    }
}

