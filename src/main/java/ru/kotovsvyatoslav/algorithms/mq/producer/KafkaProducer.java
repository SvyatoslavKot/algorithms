package ru.kotovsvyatoslav.algorithms.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class KafkaProducer implements MQProducer {
    @Autowired
    @Qualifier("myKafkaTemplate")
    private KafkaTemplate myKafkaTemplate;



    public void produce(String topic, Object message)  {
        log.info("Produce message , message{" + message +"}");
        try {
            myKafkaTemplate.send(topic, message).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
