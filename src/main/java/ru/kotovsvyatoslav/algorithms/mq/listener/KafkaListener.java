package ru.kotovsvyatoslav.algorithms.mq.listener;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kotovsvyatoslav.algorithms.parser.StringToIntegerArray;
import ru.kotovsvyatoslav.algorithms.sort.BubbleSort;
import ru.kotovsvyatoslav.algorithms.sort.QuickSort;

@Component
public class KafkaListener {

    private StringToIntegerArray stringToIntegerArray = new StringToIntegerArray();
    @Autowired
    private BubbleSort bubbleSort;
    @Autowired
    private QuickSort quickSort;

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

    @org.springframework.kafka.annotation.KafkaListener(topics = "algorithms-sort-quick", groupId = "group3")
    public void quickSortMessage(String msg )  {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            String message = (String) jsonObject.get("message");
            String sessionId = (String) jsonObject.get("sessionId");
            System.out.println(message + " " + sessionId);
            Integer[] intArray = stringToIntegerArray.stringToArray(message);
            quickSort.sort(intArray, sessionId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //clientService.updateClient(userDto);

    }
}
