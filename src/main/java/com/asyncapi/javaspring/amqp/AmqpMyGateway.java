package com.asyncapi.javaspring.amqp;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

/**
 * @author Raisel Melian raisel.melian@gmail.com
 */
@MessagingGateway
public interface AmqpMyGateway {

    @Gateway(requestChannel = "amqpOutboundChannel")
    void sendToRabbit(String data);
}
