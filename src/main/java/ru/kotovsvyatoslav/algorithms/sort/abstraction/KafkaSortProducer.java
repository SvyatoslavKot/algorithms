package ru.kotovsvyatoslav.algorithms.sort.abstraction;

import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;

public interface KafkaSortProducer {

    void  kafkaProduceSort (Integer [] integerArray, String sessionId);

}
