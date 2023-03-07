package ru.kotovsvyatoslav.algorithms.algorithms.sort;


import ru.kotovsvyatoslav.algorithms.mq.producer.MQProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.WebSocketThreadProducer;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.AbstractSort;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.MQSortWSocketAbstractProducer;

public class SortProducer extends MQSortWSocketAbstractProducer {

    WebSocketThreadProducer threadProducer;
    AbstractSort worker;

    public SortProducer(AbstractSort abstractSort, MQProducer mqProducer, String topic) {
        super(abstractSort, mqProducer, topic);
        this.worker = super.getAbstractSort();
        this.threadProducer = super.getWebSocketThreadProducer();
    }

    @Override
    public void produceBySessionId(Integer[] integerArray, String sessionId) {

        threadProducer.setSessionId(sessionId);
        threadProducer.start();

        worker.sort(integerArray);

        synchronized (threadProducer) {
            threadProducer.setWorking(false);
            threadProducer.notifyAll();
        }
    }

}
