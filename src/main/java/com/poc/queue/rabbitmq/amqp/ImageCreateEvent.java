package com.poc.queue.rabbitmq.amqp;

public record ImageCreateEvent(
        String id,
        byte[] content
) {

    public static ImageCreateEvent from(String id, byte[] content) {
        return new ImageCreateEvent(id, content);
    }
}
