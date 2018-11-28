package com.asyncapi.javaspring.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Raisel Melian raisel.melian@gmail.com
 */
@Component
public class AmqpCommandLine implements CommandLineRunner {


    @Autowired
    AmqpMyGateway amqpMyGateway;
//    AmqpTemplate amqpTemplate;

    @Override
    public void run(String... args) {
        System.out.println("******* AMQP Sending message: *******");
//        amqpTemplate.convertAndSend("","","Hello World");
        amqpMyGateway.sendToRabbit("AMQP hello world");
        System.out.println("******* AMQP Sent: Hello World ******");
    }
}
