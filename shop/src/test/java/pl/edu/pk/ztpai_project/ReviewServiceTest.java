package pl.edu.pk.ztpai_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pk.ztpai_project.model.Review;
import pl.edu.pk.ztpai_project.model.Status;
import pl.edu.pk.ztpai_project.repository.ReviewRepository;
import pl.edu.pk.ztpai_project.service.ReviewService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private ReviewService service;

    @Test
    void shouldReturnReviewsByProductAndStatus() {
        Long productId = 1L;
        Status status = Status.ACCEPTED;

        List<Review> reviews = List.of(new Review(), new Review());

        when(repository.getAllByProductIdAndStatus(productId, status))
                .thenReturn(reviews);

        List<Review> result = service.getAllReviewsByProductAndStatus(productId, status);

        assertEquals(2, result.size());
        verify(repository).getAllByProductIdAndStatus(productId, status);
    }

    @Test
    void shouldReturnReviewsByStatus() {
        Status status = Status.ACCEPTED;

        List<Review> reviews = List.of(new Review());

        when(repository.findAllByStatus(status)).thenReturn(reviews);

        List<Review> result = service.getAllReviewsByStatus(status);

        assertEquals(1, result.size());
        verify(repository).findAllByStatus(status);
    }

    @Test
    void shouldReturnReviewsByProductId() {
        Long productId = 5L;

        List<Review> reviews = List.of(new Review(), new Review(), new Review());

        when(repository.findAllByProductId(productId)).thenReturn(reviews);

        List<Review> result = service.getAllProductReviews(productId);

        assertEquals(3, result.size());
        verify(repository).findAllByProductId(productId);
    }

    @Test
    void shouldReturnReviewByIdAndStatus() {
        Long id = 10L;
        Status status = Status.ACCEPTED;

        Review review = new Review();

        when(repository.findByIdAndStatus(id, status))
                .thenReturn(Optional.of(review));

        Optional<Review> result = service.getReviewByIdAndStatus(id, status);

        assertTrue(result.isPresent());
        verify(repository).findByIdAndStatus(id, status);
    }

    @Test
    void shouldReturnEmptyWhenReviewNotFound() {
        Long id = 10L;
        Status status = Status.ACCEPTED;

        when(repository.findByIdAndStatus(id, status))
                .thenReturn(Optional.empty());

        Optional<Review> result = service.getReviewByIdAndStatus(id, status);

        assertTrue(result.isEmpty());
        verify(repository).findByIdAndStatus(id, status);
    }

    @Test
    void shouldCreateReview() {
        Review review = new Review();
        Review saved = new Review();

        when(repository.save(review)).thenReturn(saved);

        Review result = service.createReview(review);

        assertNotNull(result);
        verify(repository).save(review);
    }

    @Test
    void shouldDeleteReviewsByProductId() {
        Long productId = 3L;

        doNothing().when(repository).deleteAllByProductId(productId);

        service.deleteAllProductReviews(productId);

        verify(repository).deleteAllByProductId(productId);
    }

    @Test
    void shouldDeleteReviewById() {
        Long id = 7L;

        doNothing().when(repository).deleteById(id);

        service.deleteReviewWithId(id);

        verify(repository).deleteById(id);
    }
}