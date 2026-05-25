package pl.edu.pk.ztpai_project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pk.ztpai_project.dto.Review.NewReviewResponse;
import pl.edu.pk.ztpai_project.dto.Review.ReviewMapper;
import pl.edu.pk.ztpai_project.dto.Review.ReviewRequest;
import pl.edu.pk.ztpai_project.dto.Review.ReviewResponse;
import pl.edu.pk.ztpai_project.messaging.event.ReviewCreatedEvent;
import pl.edu.pk.ztpai_project.messaging.publisher.ReviewEventPublisher;
import pl.edu.pk.ztpai_project.model.Review;
import pl.edu.pk.ztpai_project.model.Status;
import pl.edu.pk.ztpai_project.service.ReviewService;

@RestController
@RequestMapping("/api/products/{productId}/reviews")
public class ReviewController {

    private final ReviewService service;
    private final ReviewEventPublisher publisher;

    public ReviewController(ReviewService service, ReviewEventPublisher publisher) {
        this.publisher = publisher;
        this.service = service;
    }

    @GetMapping
    public List<ReviewResponse> getAll(@PathVariable Long productId) {
        return service.getAllReviewsByProductAndStatus(productId, Status.ACCEPTED)
        .stream()
        .map(ReviewMapper::toResponse)
        .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getById(@PathVariable Long id) {
        return service.getReviewByIdAndStatus(id, Status.ACCEPTED)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NewReviewResponse createReview(@PathVariable Long productId, @RequestBody ReviewRequest request) {
        Review entity = ReviewMapper.toEntity(productId, request);
        entity.setProductId(productId);
        Review reviewSaved = service.createReview(entity);

        publisher.publishReviewCreated(
            new ReviewCreatedEvent(
                reviewSaved.getId(),
                reviewSaved.getProductId(),
                reviewSaved.getReview()
            )
        );

        return ReviewMapper.toNewResponse(reviewSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteReviewWithId(id);
    }
}
