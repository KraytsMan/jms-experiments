package com.kraytsman.jms.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;

import javax.jms.ConnectionFactory;

@SpringBootApplication
public class Application {

    /*
    Please run this command before start jms queue registry :
    docker run -p 61616:61616 -p 8161:8161 rmohr/activemq:5.14.3
     */

    @Bean
    public JmsInvokerServiceExporter exporter(JmsTemplate implementation) {
        JmsInvokerServiceExporter exporter = new JmsInvokerServiceExporter();
        exporter.setServiceInterface(JmsOperations.class);
        exporter.setService(implementation);
        return exporter;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer1(ConnectionFactory factory, JmsInvokerServiceExporter exporter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setDestinationName("client1ToClient2");
        container.setConcurrentConsumers(1);
        container.setMessageListener(exporter);
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer2(ConnectionFactory factory, JmsInvokerServiceExporter exporter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setDestinationName("client2ToClient1");
        container.setConcurrentConsumers(1);
        container.setMessageListener(exporter);
        return container;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
