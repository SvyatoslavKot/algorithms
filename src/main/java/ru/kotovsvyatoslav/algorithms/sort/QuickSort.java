package ru.kotovsvyatoslav.algorithms.sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaThreadProducer;

@Component
public class QuickSort {

    @Autowired
    KafkaProducer kafkaProducer;
    private KafkaThreadProducer producer;
    private String messageProduce = new String();

    public void sort(Integer[] integers, String sessionId) {
        producer = new KafkaThreadProducer(kafkaProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_QUICK_ANSWER.getValue(), sessionId);
        messageProduce = messageProduce + "Start Sort Quick";
        addMessage(messageProduce);
        producer.start();

        quickSort(integers,0, integers.length-1);

        addMessage(arrayToString(integers));

        addMessage("End Sort Quick");
        producer.setSorting(false);

    }

    private  void quickSort (Integer[] arr, int from, int to) {
        if ( from < to ) {
            int divideIndex = partition(arr, from,to);
            addMessage("Divide index -> " + divideIndex);
            quickSort(arr, from, divideIndex-1);
            quickSort(arr, divideIndex, to);
        }
    }

    private int partition(Integer[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[from];
        while (leftIndex <= rightIndex) {
            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }
            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {

                addMessage("Partition  method -> indexFrom ->"+ from + ", indexTo -> "+ to + "array -> " +  arrayToString(arr));
                swap(arr,rightIndex,leftIndex);
                addMessage("Partition  method after swap  array -> " +  arrayToString(arr));

                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(Integer[] arr, int rightIndex, int leftIndex) {
        int tmp = arr[rightIndex];
        arr[rightIndex] = arr[leftIndex];
        arr[leftIndex] = tmp;

    }

    private synchronized void addMessage(String msg) {
        synchronized (producer) {
            producer.addMessage(msg);
            producer.notifyAll();
        }
    }

    private String arrayToString ( Integer[] integers) {
        String s = "";
        for (Integer i : integers) {
            s = s + i.toString() + ", ";
        }
        return s;
    }

}
