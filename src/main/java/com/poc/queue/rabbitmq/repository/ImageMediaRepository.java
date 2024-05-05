package com.poc.queue.rabbitmq.repository;

import com.poc.queue.rabbitmq.models.ImageMedia;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ImageMediaRepository {

    private final Map<String, ImageMedia> imageMediaMap = new ConcurrentHashMap<>();

    public ImageMedia save(ImageMedia imageMedia) {
        imageMediaMap.put(imageMedia.getId(), imageMedia);
        return imageMedia;
    }

    public List<ImageMedia> findAll() {
        return List.copyOf(imageMediaMap.values());
    }

    public ImageMedia findById(String id) {
        return imageMediaMap.get(id);
    }

    public ImageMedia update(ImageMedia imageMedia) {
        imageMediaMap.put(imageMedia.getId(), imageMedia);
        return imageMedia;
    }

    public void delete(String id) {
        imageMediaMap.remove(id);
    }
}
