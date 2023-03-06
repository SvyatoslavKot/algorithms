package ru.kotovsvyatoslav.algorithms.mq.producer;

import java.util.HashMap;

public class KafkaThreadProducer extends KafkaAbstractThreadProducer{

    private String sessionId;

    public KafkaThreadProducer(KafkaProducer kafkaProducer, String topic) {
        super(kafkaProducer, topic);
    }

    @Override
    void messageProd() {
        HashMap<String, String> msgMag = new HashMap<>();
        msgMag.put("sessionId", sessionId);
        msgMag.put("message", msgQueue.poll());

        kafkaProducer.produce(topic, msgMag);
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}

