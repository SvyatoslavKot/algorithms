package ru.kotovsvyatoslav.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;

public abstract class KafkaSortProducer {
    KafkaThreadProducer producer;
    String messageProduce = new String();

    public abstract void  kafkaProduceSort (Integer [] integerArray, String sessionId);
}
