package com.kraytsman.jms.client2;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "client1ToClient2")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

}
