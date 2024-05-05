package com.poc.queue.rabbitmq.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.queue.rabbitmq.configuration.ObjectMapperConfig;
import com.poc.queue.rabbitmq.models.MediaStatus;
import com.poc.queue.rabbitmq.repository.ImageMediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageCreateListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageCreateListener.class);

    private final ImageMediaRepository imageMediaRepository;

    public ImageCreateListener(ImageMediaRepository imageMediaRepository) {
        this.imageMediaRepository = imageMediaRepository;
    }

    @RabbitListener(queues = "${amqp.queues.image-created.queue}")
    public void consume(@Payload final String message) throws JsonProcessingException {
        LOGGER.info("Received message: " + message);
        final var dto = ObjectMapperConfig.readValue(message, ImageCreateEvent.class);
        final var imageMedia = imageMediaRepository.findById(dto.id());

        // Simulate creating the image at the storage
        final var storageResult = ImageCreateMessage.from(dto.id(), UUID.randomUUID().toString());

        imageMedia.updateLocationAndStatus(storageResult.key(), MediaStatus.COMPLETED);
        imageMediaRepository.update(imageMedia);
    }
}
