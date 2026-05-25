package pl.edu.pk.ztpai_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.edu.pk.ztpai_project.model.Review;
import pl.edu.pk.ztpai_project.model.Status;
import pl.edu.pk.ztpai_project.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getAllReviewsByProductAndStatus(Long productId, Status status) {
        return repository.getAllByProductIdAndStatus(productId, status);
    }


    public List<Review> getAllReviewsByStatus(Status status) {
        return repository.findAllByStatus(status);
    }

    public List<Review> getAllProductReviews(Long productId) {
        return repository.findAllByProductId(productId);
    }

    public Optional<Review> getReviewByIdAndStatus(Long id, Status status) {
        return repository.findByIdAndStatus(id, status);
    }

    public Review createReview(Review review) {
        return repository.save(review);
    }

    public void deleteAllProductReviews(Long productId) {
        repository.deleteAllByProductId(productId);
    };

    public void deleteReviewWithId(Long id) {
        repository.deleteById(id);
    }
}

