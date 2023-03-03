package ru.kotovsvyatoslav.algorithms.util;

import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;

public class KafkaMessageSender extends MessageSender {

    private KafkaThreadProducer producer;

    public KafkaMessageSender(KafkaThreadProducer producer) {
        this.producer = producer;
    }

    @Override
    public synchronized void messageSend(String msg) {
        synchronized (producer){
            producer.addMessage(msg);
            producer.notifyAll();
        }
    }


}
