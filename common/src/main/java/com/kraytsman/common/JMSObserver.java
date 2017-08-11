package com.kraytsman.common;

import org.springframework.jms.core.JmsTemplate;

public class JMSObserver implements MyObserver {

    InputTextListener inputTextListener;
    JmsTemplate jmsTemplate;
    private String queue;
    private String consumer;

    public JMSObserver(InputTextListener inputTextListener, JmsTemplate jmsTemplate, String queue, String consumer) {
        this.inputTextListener = inputTextListener;
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
        this.consumer = consumer;
    }

    @Override
    public void update() {
        String message = this.inputTextListener.getMessage();
        this.jmsTemplate.convertAndSend(queue, message);
    }

}
