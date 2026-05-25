package pl.edu.pk.ztpai_project.messaging.event;

public record ReviewCreatedEvent (
    Long id,
    Long productId,
    String review
) {}
