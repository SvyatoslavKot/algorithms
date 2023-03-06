package ru.kotovsvyatoslav.algorithms.mq.listener;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.parser.StringToIntegerArray;
import ru.kotovsvyatoslav.algorithms.sort.*;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.KafkaSortProducerAbstract;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaListener {

    private StringToIntegerArray stringToIntegerArray = new StringToIntegerArray();
    @Autowired
    private KafkaProducer kafkaProducer;
    private KafkaSortProducerAbstract sortProducer;



    @org.springframework.kafka.annotation.KafkaListener(topics = "algorithms-sort-bubble", groupId = "group3")
    public void bubbleSortMessage(String msg )  {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String message = (String) jsonObject.get("message");
            String sessionId = (String) jsonObject.get("sessionId");
            try {
                Integer[] intArray = stringToIntegerArray.stringToArray(message);
                sortProducer = Sorts.newSortProducer(new BubbleSort(), kafkaProducer);
                sortProducer.kafkaProduceSort(intArray, sessionId);
            }catch (NumberFormatException e) {
                Map<String, String> msp = new HashMap<>();
                msp.put("sessionId", sessionId);
                msp.put("message", "В ведите числа через запятуюб без пробела <1,2,3> " + e.getMessage());
                kafkaProducer.produce(KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue(), msp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "algorithms-sort-quick", groupId = "group3")
    public void quickSortMessage(String msg )  {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String message = (String) jsonObject.get("message");
            String sessionId = (String) jsonObject.get("sessionId");
            Integer[] intArray;
            try{
                intArray = stringToIntegerArray.stringToArray(message);
                if (intArray != null) {
                    sortProducer = Sorts.newSortProducer(new QuickSort(), kafkaProducer);
                    sortProducer.kafkaProduceSort(intArray, sessionId);
                }

            }catch (NumberFormatException e) {
                Map<String, String> msp = new HashMap<>();
                msp.put("sessionId", sessionId);
                msp.put("message", "В ведите числа через запятуюб без пробела <1,2,3> " + e.getMessage());
                kafkaProducer.produce(KafkaSettings.TOPIC_ALGORITHMS_SORT_QUICK_ANSWER.getValue(), msp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
