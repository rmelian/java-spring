package com.asyncapi.javaspring.amqp;

import org.springframework.amqp.core.MessageListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Raisel Melian raisel.melian@gmail.com
 */
@Service
public class AmqpMessageHandler {

    public void handleMessage1(Message<?> message) throws MessagingException {
        System.out.println("->>>>>>>>1 Received: " + message.getPayload());
    }

    public void handleMessage2(Message<?> message) throws MessagingException {
        System.out.println("->>>>>>>>2 Received: " + message.getPayload());
    }

    public void handleMessage3(Message<?> message) throws MessagingException {
        System.out.println("->>>>>>>>3 Received: " + message.getPayload());
    }
}
