package com.asyncapi.javaspring.mqtt;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author Raisel Melian raisel.melian@gmail.com
 */
@Service
public class MqttMessageHandler {

    public void handleMessage1(Message<?> message) {
        System.out.println("received1 from MQTT");
        System.out.println("=======================");
        System.out.println(message.getPayload());
        System.out.println("=======================");
    }

    public void handleMessage2(Message<?> message) {
        System.out.println("received2 from MQTT");
        System.out.println("=======================");
        System.out.println(message.getPayload());
        System.out.println("=======================");
    }
}
