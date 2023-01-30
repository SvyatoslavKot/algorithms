package ru.kotovsvyatoslav.algorithms.mq;

public enum KafkaSettings {

    TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER("algorithms-sort-bubble-answer");


    private String value;

    KafkaSettings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
