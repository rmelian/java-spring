package com.asyncapi.javaspring.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Raisel Melian raisel.melian@gmail.com
 */
@Component
public class MqttCommandLine implements CommandLineRunner {


    @Autowired
    MqttMyGateway myGateway;

    @Override
    public void run(String... args) {
        System.out.println("******* MQTT Sending message: *******");
        myGateway.sendMessage("Hello World");
        System.out.println("MQTT Sent: Hello World");
    }
}
