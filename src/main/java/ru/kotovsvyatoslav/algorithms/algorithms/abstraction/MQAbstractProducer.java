package ru.kotovsvyatoslav.algorithms.algorithms.abstraction;

import ru.kotovsvyatoslav.algorithms.mq.producer.WebSocketThreadProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.MQAbstractThreadProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.MQProducer;
import ru.kotovsvyatoslav.algorithms.util.messageSender.MQMessageSender;

public abstract class MQAbstractProducer {

    private MQProducer mqProducer;
    private MQAbstractThreadProducer mqAbstractThreadProducer;
    private String topic;

    public MQAbstractProducer( MQProducer mqProducer, String topic) {
        this.mqProducer = mqProducer;
        this.topic = topic;
        //this.mqAbstractThreadProducer = new WebSocketThreadProducer(mqProducer,topic);
    }

    public MQAbstractThreadProducer getMqAbstractThreadProducer() {
        return mqAbstractThreadProducer;
    }

    public void setMqAbstractThreadProducer(MQAbstractThreadProducer mqAbstractThreadProducer) {
        this.mqAbstractThreadProducer = mqAbstractThreadProducer;
    }

    public MQProducer getMqProducer() {
        return mqProducer;
    }

    public String getTopic() {
        return topic;
    }
}
