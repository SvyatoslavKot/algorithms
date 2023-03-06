package ru.kotovsvyatoslav.algorithms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static ru.kotovsvyatoslav.algorithms.config.JsonSerializer.OBJECT_MAPPER;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServer;

    @Bean
    public KafkaTemplate<String, Object> myKafkaTemplate () {
        return new KafkaTemplate<>(producerFactoryJson());
    }

    @Bean
    public Map<String, Object> producerConfigJson() {
        Map <String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        props.put(ProducerConfig.ACKS_CONFIG,"1");
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33_544_432);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 1_000);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        props.put(OBJECT_MAPPER, new ObjectMapper());

        return props;
    }

    @Bean
    public Map<String, Object> consumerConfigJson() {
        Map <String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group3");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(OBJECT_MAPPER, new ObjectMapper());
        //props.put(TYPE_REFERENCE, new TypeReference<UserDTO>(){});

        return props;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactoryJson() {
        return  new DefaultKafkaProducerFactory<>(producerConfigJson());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return  new DefaultKafkaConsumerFactory<>(consumerConfigJson());
    }
}
