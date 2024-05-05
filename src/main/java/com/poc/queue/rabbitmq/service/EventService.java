package com.poc.queue.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EventService {
    void send(Object event) throws JsonProcessingException;
}

