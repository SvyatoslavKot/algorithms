package ru.kotovsvyatoslav.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.AbstractSort;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.KafkaSortProducerAbstract;

public class SortProducer extends KafkaSortProducerAbstract {


    public SortProducer(AbstractSort abstractSort, KafkaProducer kafkaProducer, String KAFKA_TOPIC) {
        super(abstractSort, kafkaProducer, KAFKA_TOPIC);
    }

    @Override
    public void kafkaProduceSort(Integer[] integerArray, String sessionId) {
        kafkaThreadProducer.setSessionId(sessionId);

        kafkaThreadProducer.start();
        abstractSort.sort(integerArray);

        synchronized (kafkaThreadProducer) {
            kafkaThreadProducer.setSorting(false);
            kafkaThreadProducer.notifyAll();
        }
    }

}
