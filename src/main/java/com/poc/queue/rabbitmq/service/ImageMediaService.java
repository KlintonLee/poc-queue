package com.poc.queue.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.queue.rabbitmq.amqp.ImageCreateEvent;
import com.poc.queue.rabbitmq.dto.ImageMediaDto;
import com.poc.queue.rabbitmq.models.ImageMedia;
import com.poc.queue.rabbitmq.models.MediaStatus;
import com.poc.queue.rabbitmq.repository.ImageMediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageMediaService {

    private final ImageMediaRepository repository;

    private final RabbitEventService eventService;

    public ImageMediaService(final ImageMediaRepository repository, final RabbitEventService eventService) {
        this.repository = Objects.requireNonNull(repository);
        this.eventService = Objects.requireNonNull(eventService);
    }

    public ImageMedia save(final ImageMediaDto imageMedia) throws JsonProcessingException {
        final var id = UUID.randomUUID().toString();
        final var fileName = imageMedia.file().getOriginalFilename();
        final var contentType = imageMedia.file().getContentType();
        final var image = ImageMedia.create(id, fileName, contentType, null, MediaStatus.PENDING);
        final var imageEvent = ImageCreateEvent.from(id, imageMedia.file());

        this.repository.save(image);
        eventService.send(imageEvent);
        return image;
    }

    public List<ImageMedia> list() {
        return this.repository.findAll();
    }

    public ImageMedia findById(final String id) {
        return this.repository.findById(id);
    }
}
