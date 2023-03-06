package ru.kotovsvyatoslav.algorithms.sort.abstraction;

import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;
import ru.kotovsvyatoslav.algorithms.util.KafkaMessageSender;

public abstract class KafkaSortProducerAbstract implements KafkaSortProducer{

    public AbstractSort abstractSort;
    public KafkaProducer kafkaProducer;
    public KafkaThreadProducer kafkaThreadProducer;
    public String KAFKA_TOPIC;

    public KafkaSortProducerAbstract(AbstractSort abstractSort, KafkaProducer kafkaProducer, String KAFKA_TOPIC) {
        this.abstractSort = abstractSort;
        this.kafkaProducer = kafkaProducer;
        this.KAFKA_TOPIC = KAFKA_TOPIC;
        this.kafkaThreadProducer = new KafkaThreadProducer(kafkaProducer,KAFKA_TOPIC);
        this.abstractSort.setMessageSender(new KafkaMessageSender(kafkaThreadProducer));
    }
}
