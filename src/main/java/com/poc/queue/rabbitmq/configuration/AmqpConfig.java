package com.poc.queue.rabbitmq.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    @ConfigurationProperties("amqp.queue.image-created")
    public QueueProperties imageCreatedQueueProperties() {
        return new QueueProperties();
    }
}
