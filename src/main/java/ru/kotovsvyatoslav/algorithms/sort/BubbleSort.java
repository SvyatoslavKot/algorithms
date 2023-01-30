package ru.kotovsvyatoslav.algorithms.sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;

import java.util.HashMap;
import java.util.Map;

@Component
public class BubbleSort {

    @Autowired
    KafkaProducer kafkaProducer;

    public void sort (Integer [] integerArray, String sessionId) {
        Map<String, String> msgMap = new HashMap<>();
        boolean isSorted = false;
        int indexI = 1;

        while (!isSorted) {
            isSorted = true;

            for (int i = indexI; i < integerArray.length; i++) {
                if (integerArray[i] < integerArray[i - 1]) {
                    int t = integerArray[i];
                    integerArray[i] = integerArray[i - 1];
                    integerArray[i - 1] = t;
                    isSorted = false;
                }
            }

        String message = " ";

            for (Integer integer : integerArray) {
            message = message + integer.toString() + " ";
        }

        msgMap.put("sessionId", sessionId);
        msgMap.put("message", message);
            System.out.println(msgMap.get("sessionId") + msgMap.get("message"));
        kafkaProducer.produce(KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue(), msgMap);
        }
        msgMap.put("sessionId", sessionId);
        msgMap.put("message", "sort END");
        kafkaProducer.produce(KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue(), msgMap);

    }


}
