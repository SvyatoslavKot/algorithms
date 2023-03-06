package ru.kotovsvyatoslav.algorithms.util;

import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;

public class KafkaMessageSender extends MessageSender {

    private KafkaThreadProducer kafkaThreadProducer;

    public KafkaMessageSender(KafkaThreadProducer kafkaThreadProducer) {
        this.kafkaThreadProducer = kafkaThreadProducer;
    }

    @Override
    public synchronized void messageSend(String msg) {
        synchronized (kafkaThreadProducer){
            kafkaThreadProducer.addMessage(msg);
            kafkaThreadProducer.notifyAll();
        }
    }


}
