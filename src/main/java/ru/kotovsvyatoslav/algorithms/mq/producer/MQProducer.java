package ru.kotovsvyatoslav.algorithms.mq.producer;

public interface MQProducer {

    void produce(String topic, Object message);
}
