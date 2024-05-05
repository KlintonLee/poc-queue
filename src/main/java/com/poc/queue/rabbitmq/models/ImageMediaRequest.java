package com.poc.queue.rabbitmq.models;

public record ImageMediaRequest(
        String name,
        String contentType
) {
}
