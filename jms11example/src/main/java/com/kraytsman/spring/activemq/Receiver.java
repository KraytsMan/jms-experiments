package com.kraytsman.spring.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "messageBox", containerFactory = "messageFactory")
    public void receiveMessage(Message message) {
        System.out.println("Received message: " + message);
    }
}
