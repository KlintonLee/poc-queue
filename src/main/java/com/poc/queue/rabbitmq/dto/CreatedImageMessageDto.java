package com.poc.queue.rabbitmq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreatedImageMessageDto(
        @JsonProperty("id") String id,
        @JsonProperty("location") String key
) {
}
