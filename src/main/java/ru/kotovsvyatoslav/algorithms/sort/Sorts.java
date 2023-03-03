package ru.kotovsvyatoslav.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.Sortable;

public class Sorts {

    public static Sortable newBubbleSort(){
        return new BubbleSort();
    }

    public static Sortable newQuickSort() {
        return new QuickSort();
    }

    public static SortProducer newSortProducer(Sortable sort, KafkaProducer kafkaProducer) {
        String topic = new String();
        if (sort.getClass().equals(BubbleSort.class)){
            topic = KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue();
        }else if (sort.getClass().equals(QuickSort.class)){
            topic = KafkaSettings.TOPIC_ALGORITHMS_SORT_QUICK_ANSWER.getValue();
        }else if (sort.getClass().equals(MergeSort.class)){
            topic = KafkaSettings.TOPIC_ALGORITHMS_SORT_MERGE_ANSWER.getValue();
        }else if (sort.getClass().equals(SelectSort.class)){
            topic = KafkaSettings.TOPIC_ALGORITHMS_SORT_SELECT_ANSWER.getValue();
        }else {
            new RuntimeException("SortProduce Not Found");
        }
        return new SortProducer(sort,kafkaProducer, topic);

    }
}
