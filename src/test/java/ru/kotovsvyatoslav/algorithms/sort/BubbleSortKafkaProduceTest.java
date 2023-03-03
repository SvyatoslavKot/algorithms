package ru.kotovsvyatoslav.algorithms.sort;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kotovsvyatoslav.algorithms.mq.producer.KafkaProducer;
import ru.kotovsvyatoslav.algorithms.sort.abstraction.Sortable;

@SpringBootTest
class BubbleSortKafkaProduceTest {

    @Autowired
    KafkaProducer kafkaProducer;



    @Test
    public void bubbleSortTest(){
        Sortable sort = Sorts.newBubbleSort();
        Integer[] array = new Integer[]{12,13,45,43,11,2,34,21,2,0};
        sort.sort(array);
    }
}