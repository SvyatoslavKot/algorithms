package ru.kotovsvyatoslav.algorithms.algorithms.sort;

import ru.kotovsvyatoslav.algorithms.mq.KafkaSettings;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.MQProducer;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.AbstractSort;
import ru.kotovsvyatoslav.algorithms.algorithms.sort.abstraction.MQSortWSocketAbstractProducer;

public class SortFactory {

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

    public static AbstractSort newInsertionSort() {
        return new InsertionSort();
    }

    public static AbstractSort newCountSort() {
        return new CountSort();
    }

    public static AbstractSort newCombSort() {
        return new CombSort();
    }

    public static MQSortWSocketAbstractProducer newWSocketSortProducer(AbstractSort abstractSort, MQProducer mqProducer) {
        if (mqProducer.getClass().equals(KafkaProducer.class)){
            if (abstractSort.getClass().equals(BubbleSort.class)){
                return new SortProducer(abstractSort,mqProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue());
            }else if (abstractSort.getClass().equals(QuickSort.class)){
                return new SortProducer(abstractSort,mqProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_QUICK_ANSWER.getValue());
            }else if (abstractSort.getClass().equals(MergeSort.class)){
                return new SortProducer(abstractSort,mqProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_MERGE_ANSWER.getValue());
            }else if (abstractSort.getClass().equals(SelectSort.class)){
                return new SortProducer(abstractSort,mqProducer, KafkaSettings.TOPIC_ALGORITHMS_SORT_SELECT_ANSWER.getValue());
            }else {
                throw new RuntimeException("SortProduce Not Found");
            }
        }
        throw new RuntimeException("Wrong type class mqProducer");

    }
}
