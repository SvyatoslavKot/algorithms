package ru.kotovsvyatoslav.algorithms.mq;

public enum KafkaSettings {

    TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER("algorithms-sort-bubble-answer"),
    TOPIC_ALGORITHMS_SORT_MERGE_ANSWER("algorithms-sort-merge-answer"),
    TOPIC_ALGORITHMS_SORT_SELECT_ANSWER("algorithms-sort-select-answer"),
    TOPIC_ALGORITHMS_SORT_QUICK_ANSWER("algorithms-sort-quick-answer");

    private String value;

    KafkaSettings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
