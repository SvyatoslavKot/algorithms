package ru.kotovsvyatoslav.algorithms.mq.producer;

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
        sorting=true;
        boolean producing = true;
        while (producing){
            if (msgQueue.isEmpty() && sorting){
                try {
                    synchronized (this){
                        this.wait();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (msgQueue.isEmpty() && !sorting){
                producing = false;
            }else {
                HashMap<String, String> msgMag = new HashMap<>();
                msgMag.put("sessionId", sessionId);
                msgMag.put("message", msgQueue.poll());

                kafkaProducer.produce(topic, msgMag);
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

