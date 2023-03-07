package ru.kotovsvyatoslav.algorithms.util.messageSender;

import ru.kotovsvyatoslav.algorithms.mq.producer.MQAbstractThreadProducer;

public class MQMessageSender implements MessageSender {

    private MQAbstractThreadProducer mqThreadProducer;

    public MQMessageSender(MQAbstractThreadProducer mqThreadProducer) {
        this.mqThreadProducer = mqThreadProducer;
    }

    @Override
    public synchronized void messageSend(String msg) {
        synchronized (mqThreadProducer){
            mqThreadProducer.addMessage(msg);
            mqThreadProducer.notifyAll();
        }
    }
}
