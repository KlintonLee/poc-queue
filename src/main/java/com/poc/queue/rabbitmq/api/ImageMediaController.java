package com.poc.queue.rabbitmq.api;

import com.poc.queue.rabbitmq.dto.ImageMediaDto;
import com.poc.queue.rabbitmq.models.ImageMedia;
import com.poc.queue.rabbitmq.service.ImageMediaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageMediaController {

    private final ImageMediaService imageMediaService;

    public ImageMediaController(ImageMediaService imageMediaService) {
        this.imageMediaService = imageMediaService;
    }

    @PostMapping
    public void createImage(
            @RequestBody final MultipartFile file
    ) throws IOException {
        final var imageMediaRequest = ImageMediaDto.from(file);
        this.imageMediaService.save(imageMediaRequest);
    }

    @GetMapping
    public List<ImageMedia> listImages() {
        return this.imageMediaService.list();
    }

    @GetMapping("/{id}")
    public ImageMedia getImage(
            @PathVariable final String id
    ) {
        return this.imageMediaService.findById(id);
    }
}
