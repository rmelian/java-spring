package com.asyncapi.javaspring.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.StringUtils;

/**
 * @author Raisel Melian raisel.melian@gmail.com
 */
@Configuration
public class MqttConfig {

    @Value("${mqtt.broker.host}")
    private String host;

    @Value("${mqtt.broker.port}")
    private int port;

    @Value("${mqtt.broker.username}")
    private String username;

    @Value("${mqtt.broker.password}")
    private String password;

    @Value("${mqtt.topic.topic1}")
    private String topic1;

    @Value("${mqtt.topic.topic2}")
    private String topic2;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] { host + ":" + port });
        if (!StringUtils.isEmpty(username)) {
            options.setUserName(username);
        }
        if (!StringUtils.isEmpty(password)) {
            options.setPassword(password.toCharArray());
        }
        factory.setConnectionOptions(options);
        return factory;
    }

    // consumer

//    @Bean
//    public MessageChannel mqttInputChannel() {
//        return new DirectChannel();
//    }

    @Autowired
    MqttMessageHandler mqttMessageHandler;

    @Bean
    public IntegrationFlow mqttInFlow1() {
        return IntegrationFlows.from(mqttInbound1())
                .transform(p -> p + ", received from MQTT")
                .handle(mqttMessageHandler::handleMessage1)
                .get();
    }

    @Bean
    public IntegrationFlow mqttInFlow2() {
        return IntegrationFlows.from(mqttInbound2())
                .transform(p -> p + ", received from MQTT")
                .handle(mqttMessageHandler::handleMessage2)
                .get();
    }

    @Bean
    public MessageProducerSupport mqttInbound1() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer",
                mqttClientFactory(), topic1);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        return adapter;
    }

    @Bean
    public MessageProducerSupport mqttInbound2() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("siSampleConsumer2",
                mqttClientFactory(), topic2);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        return adapter;
    }

    // publisher

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("siSampleTopic");
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

}
