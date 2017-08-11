package com.kraytsman.jms.client1;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "client2ToClient1")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

}
