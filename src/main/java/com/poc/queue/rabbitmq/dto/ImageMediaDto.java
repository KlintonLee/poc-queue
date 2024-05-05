package com.poc.queue.rabbitmq.dto;

import org.springframework.web.multipart.MultipartFile;

public record ImageMediaDto(
        MultipartFile file
) {

    public static ImageMediaDto from(MultipartFile file) {
        return new ImageMediaDto(file);
    }
}
