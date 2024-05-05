package com.poc.queue.rabbitmq.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageCreateMessage(
        @JsonProperty("id") String id,
        @JsonProperty("location") String key
) {

    public static ImageCreateMessage from(String id, String key) {
        return new ImageCreateMessage(id, key);
    }
}
