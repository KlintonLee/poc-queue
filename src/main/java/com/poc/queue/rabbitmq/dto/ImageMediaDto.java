package com.poc.queue.rabbitmq.dto;

import org.springframework.web.multipart.MultipartFile;

public record ImageMediaDto(
        String id,
        MultipartFile file
) {

    public static ImageMediaDto from(String id, MultipartFile file) {
        return new ImageMediaDto(id, file);
    }
}
