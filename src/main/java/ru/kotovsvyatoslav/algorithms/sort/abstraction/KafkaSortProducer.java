package ru.kotovsvyatoslav.algorithms.sort.abstraction;


public interface KafkaSortProducer {

    void  kafkaProduceSort (Integer [] integerArray, String sessionId);

}
