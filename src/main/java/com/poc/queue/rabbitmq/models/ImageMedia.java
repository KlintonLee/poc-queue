package com.poc.queue.rabbitmq.models;

public class ImageMedia {

    private final String id;

    private final String name;

    private final String contentType;

    private String location;

    private MediaStatus status;

    private ImageMedia(String id, String name, String contentType, String location, MediaStatus status) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.location = location;
        this.status = status;
    }

    public static ImageMedia create(
            final String id,
            final String name,
            final String contentType,
            final String location,
            final MediaStatus status
    ) {
        return new ImageMedia(id, name, contentType, location, status);
    }

    public ImageMedia updateLocationAndStatus(final String location, final MediaStatus status) {
        setLocation(location);
        setStatus(status);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public String getLocation() {
        return location;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public MediaStatus getStatus() {
        return status;
    }

    private void setStatus(MediaStatus status) {
        this.status = status;
    }
}
