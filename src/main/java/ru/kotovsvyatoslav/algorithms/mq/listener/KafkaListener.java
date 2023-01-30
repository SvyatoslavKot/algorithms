package ru.kotovsvyatoslav.algorithms.mq.listener;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.parser.StringToIntegerArray;
import ru.kotovsvyatoslav.algorithms.sort.BubbleSort;

import java.util.LinkedList;
import java.util.Map;

@Component
public class KafkaListener {

    private StringToIntegerArray stringToIntegerArray = new StringToIntegerArray();
    @Autowired
    private BubbleSort bubbleSort;

    @org.springframework.kafka.annotation.KafkaListener(topics = "algorithms-sort-bubble", groupId = "group3")
    public void bubbleSortMessage(String msg )  {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String message = (String) jsonObject.get("message");
            String sessionId = (String) jsonObject.get("sessionId");
            System.out.println(message + " " + sessionId);
            Integer[] intArray = stringToIntegerArray.stringToArray(message);
            bubbleSort.sort(intArray, sessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //clientService.updateClient(userDto);

    }
}
