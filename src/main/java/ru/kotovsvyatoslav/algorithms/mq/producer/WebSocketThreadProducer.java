package ru.kotovsvyatoslav.algorithms.mq.producer;

import java.util.HashMap;

public class WebSocketThreadProducer extends MQAbstractThreadProducer {

    private String sessionId;
    private MQProducer producer;

    public WebSocketThreadProducer(MQProducer producer, String topic) {
        super(producer, topic);
        this.producer = producer;
    }

    @Override
    void messageProd() {

        HashMap<String, String> msgMag = new HashMap<>();
        msgMag.put("sessionId", sessionId);
        msgMag.put("message", getMsgQueue().poll());

        producer.produce(getTopic(), msgMag);
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}

