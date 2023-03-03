package ru.kotovsvyatoslav.algorithms.sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class BubbleSort extends KafkaSortProducer implements Sortable {

    @Autowired
    KafkaProducer kafkaProducer;
    //private KafkaThreadProducer producer;
    //private String messageProduce = new String();

    public synchronized void  kafkaProduceSort (Integer [] integerArray, String sessionId) {
        producer  = new KafkaThreadProducer(kafkaProducer,KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue(),sessionId);
        producer.start();
        addMessage("Start Bubble sort");

        boolean isSorted = false;
        int indexI = 1;
        while (!isSorted) {
            isSorted = true;
            messageProduce = "";

            for (int i = indexI; i < integerArray.length; i++) {
                if (integerArray[i] < integerArray[i - 1]) {
                    int t = integerArray[i];
                    integerArray[i] = integerArray[i - 1];
                    integerArray[i - 1] = t;
                    isSorted = false;
                }
            }
            for (Integer integer : integerArray) {
                messageProduce = messageProduce + integer.toString() + " ";
        }
            addMessage(messageProduce);
        }
        addMessage("Sort End");

        synchronized (producer) {
            producer.setSorting(false);
            producer.notifyAll();
        }
    }

    private synchronized void addMessage(String msg) {
        synchronized (producer){
            producer.addMessage(msg);
            producer.notifyAll();
        }
    }

    @Override
    public Integer[] sort(Integer[] array) {
        boolean isSorted = false;
        int indexI = 1;
        while (!isSorted) {
            isSorted = true;
            messageProduce = "";

            for (int i = indexI; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int t = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = t;
                    isSorted = false;
                }
            }
            for (Integer integer : array) {
                messageProduce = messageProduce + integer.toString() + " ";
            }
            System.out.println(messageProduce);
        }
        return array;
    }

}
