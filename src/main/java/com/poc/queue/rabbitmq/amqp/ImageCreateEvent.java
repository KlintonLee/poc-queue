package com.poc.queue.rabbitmq.amqp;

import org.springframework.web.multipart.MultipartFile;

public record ImageCreateEvent(
        String id,
        MultipartFile file
) {

    public static ImageCreateEvent from(String id, MultipartFile file) {
        return new ImageCreateEvent(id, file);
    }
}
