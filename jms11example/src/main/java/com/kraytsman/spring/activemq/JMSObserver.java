package com.kraytsman.spring.activemq;

import org.springframework.jms.core.JmsTemplate;

public class JMSObserver implements MyObserver {

    InputTextListener inputTextListener;
    JmsTemplate jmsTemplate;

    public JMSObserver(InputTextListener inputTextListener, JmsTemplate jmsTemplate) {
        this.inputTextListener = inputTextListener;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void update() {
        String message = this.inputTextListener.getMessage();
        jmsTemplate.convertAndSend("messageBox", new Message("receiver@example.com", message));
    }

}
