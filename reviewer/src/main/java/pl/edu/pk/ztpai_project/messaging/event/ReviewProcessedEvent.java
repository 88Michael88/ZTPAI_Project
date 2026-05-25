package pl.edu.pk.ztpai_project.messaging.event;

public record ReviewProcessedEvent(
    Long id,
    Long productId,
    String review,
    String status
) {}
