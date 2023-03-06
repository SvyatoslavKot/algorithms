package ru.kotovsvyatoslav.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.AbstractSort;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.KafkaSortProducerAbstract;

public class Sorts {

    public static AbstractSort newBubbleSort(){
        return new BubbleSort();
    }

    public static AbstractSort newQuickSort() {
        return new QuickSort();
    }

    public static AbstractSort newSelectSort() {
        return new SelectSort();
    }

    public static AbstractSort newMergeSort() {
        return new MergeSort();
    }

    public static KafkaSortProducerAbstract newSortProducer(AbstractSort abstractSort, KafkaProducer kafkaProducer) {
        if (abstractSort.getClass().equals(BubbleSort.class)){
            return new SortProducer(abstractSort,kafkaProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue());
        }else if (abstractSort.getClass().equals(QuickSort.class)){
            return new SortProducer(abstractSort,kafkaProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_QUICK_ANSWER.getValue());
        }else if (abstractSort.getClass().equals(MergeSort.class)){
            return new SortProducer(abstractSort,kafkaProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_MERGE_ANSWER.getValue());
        }else if (abstractSort.getClass().equals(SelectSort.class)){
            return new SortProducer(abstractSort,kafkaProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_SELECT_ANSWER.getValue());
        }else {
            throw new RuntimeException("SortProduce Not Found");
        }
    }
}
