package ru.kotovsvyatoslav.algorithms.mq.listener;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.BubbleSort;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.QuickSort;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.SortFactory;
import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;
import ru.kotovsvyatoslav.algorithms.mq.producer.MQProducer;
import ru.kotovsvyatoslav.algorithms.parser.StringToIntegerArray;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.MQSortWSocketAbstractProducer;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaListener {

    @Autowired
    private MQProducer kafkaProducer;
    private StringToIntegerArray stringToIntegerArray;
    private MQSortWSocketAbstractProducer sortProducer;

    public KafkaListener(@Qualifier("kafkaProducer") MQProducer kafkaProducer) {
        this.stringToIntegerArray = new StringToIntegerArray();
        this.kafkaProducer = kafkaProducer;
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "algorithms-sort-bubble", groupId = "group3")
    public void bubbleSortMessage(String msg )  {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String message = (String) jsonObject.get("message");
            String sessionId = (String) jsonObject.get("sessionId");
            try {
                Integer[] intArray = stringToIntegerArray.stringToArray(message);
                sortProducer = SortFactory.newWSocketSortProducer(new BubbleSort(), kafkaProducer);
                sortProducer.produceBySessionId(intArray, sessionId);
            }catch (NumberFormatException e) {
                Map<String, String> msp = new HashMap<>();
                msp.put("sessionId", sessionId);
                msp.put("message", "В ведите числа через запятуюб без пробела <1,2,3> " + e.getMessage());
                kafkaProducer.produce(KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue(), msp);
            }catch (RuntimeException e) {
                e.printStackTrace();
                Map<String, String> msp = new HashMap<>();
                msp.put("sessionId", sessionId);
                msp.put("message", "Что-то пошло не так. . . " + e.getMessage());
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
                    sortProducer = SortFactory.newWSocketSortProducer(new QuickSort(), kafkaProducer);
                    sortProducer.produceBySessionId(intArray, sessionId);
                }

            }catch (NumberFormatException e) {
                Map<String, String> msp = new HashMap<>();
                msp.put("sessionId", sessionId);
                msp.put("message", "В ведите числа через запятуюб без пробела <1,2,3> " + e.getMessage());
                kafkaProducer.produce(KafkaSettings.TOPIC_ALGORITHMS_SORT_QUICK_ANSWER.getValue(), msp);
            }catch (RuntimeException e) {
                e.printStackTrace();
                Map<String, String> msp = new HashMap<>();
                msp.put("sessionId", sessionId);
                msp.put("message", "Что-то пошло не так. . . " + e.getMessage());
                kafkaProducer.produce(KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue(), msp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
