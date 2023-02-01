package ru.kotovsvyatoslav.algorithms.mq;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopic {
    @Bean
    public NewTopic topic_algorithms_sort_bubble_answer() {
        return TopicBuilder
                .name(KafkaSettings.TOPIC_ALGORITHMS_SORT_BUBBLE_ANSWER.getValue())
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic_algorithms_sort_quick_answer() {
        return TopicBuilder
                .name(KafkaSettings.TOPIC_ALGORITHMS_SORT_QUICK_ANSWER.getValue())
                .partitions(1)
                .replicas(1)
                .build();
    }
}
