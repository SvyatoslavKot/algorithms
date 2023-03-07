package ru.kotovsvyatoslav.algorithms.algorithms.abstraction;

import ru.kotovsvyatoslav.algorithms.mq.producer.MQProducer;
import ru.kotovsvyatoslav.algorithms.mq.producer.WebSocketThreadProducer;

public abstract class MQWebSocketAbstractProducer extends MQAbstractProducer implements MQWebSocketProduce {

    private WebSocketThreadProducer webSocketThreadProducer;

    public MQWebSocketAbstractProducer( MQProducer mqProducer, String topic) {
        super(mqProducer, topic);
        this.webSocketThreadProducer = new WebSocketThreadProducer(mqProducer, topic);
    }

    public WebSocketThreadProducer getWebSocketThreadProducer() {
        return webSocketThreadProducer;
    }
}
