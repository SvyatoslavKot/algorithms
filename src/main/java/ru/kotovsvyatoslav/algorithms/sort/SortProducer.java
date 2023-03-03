package ru.kotovsvyatoslav.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.KafkaSortProducer;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.Sortable;
import ru.kotovsvyatoslav.algorithms.util.KafkaMessageSender;
import ru.kotovsvyatoslav.algorithms.util.MessageSender;

public class SortProducer implements KafkaSortProducer, Sortable {

    private Sortable sort;
    private KafkaProducer kafkaProducer;
    private KafkaThreadProducer producer;
    private String KAFKA_TOPIC;

    public SortProducer(Sortable sort, KafkaProducer kafkaProducer, String topic) {
        this.sort = sort;
        this.kafkaProducer = kafkaProducer;
        this.KAFKA_TOPIC = topic;
    }

    @Override
    public void kafkaProduceSort(Integer[] integerArray, String sessionId) {
        producer = new KafkaThreadProducer(kafkaProducer, KAFKA_TOPIC);

        producer.setSessionId(sessionId);
        sort.setMessageSender(new KafkaMessageSender(producer));

        producer.start();

        sort.sort(integerArray);

        synchronized (producer) {
            producer.setSorting(false);
            producer.notifyAll();
        }
    }

    @Override
    public Integer[] sort(Integer[] array) {
        return sort.sort(array);
    }

    @Override
    public synchronized void printMsg(String msg) {
        synchronized (producer){
            producer.addMessage(msg);
            producer.notifyAll();
        }
    }



    private synchronized void addMessage(String msg) {
        synchronized (producer){
            producer.addMessage(msg);
            producer.notifyAll();
        }
    }
}
