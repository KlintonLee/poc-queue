package com.poc.queue.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.queue.rabbitmq.configuration.ObjectMapperConfig;
import com.poc.queue.rabbitmq.configuration.QueueProperties;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RabbitEventService implements EventService {

    private String exchange;
    private String routingKey;
    private RabbitOperations ops;

    public RabbitEventService(
            final QueueProperties props,
            final RabbitOperations ops
    ) {
        this.exchange = Objects.requireNonNull(props).getExchange();
        this.routingKey = Objects.requireNonNull(props).getRoutingKey();
        this.ops = Objects.requireNonNull(ops);
    }

    @Override
    public void send(final Object event) throws JsonProcessingException {
        this.ops.convertAndSend(this.exchange, this.routingKey, ObjectMapperConfig.writeValueAsString(event));
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public RabbitOperations getOps() {
        return ops;
    }

    public void setOps(RabbitOperations ops) {
        this.ops = ops;
    }
}
