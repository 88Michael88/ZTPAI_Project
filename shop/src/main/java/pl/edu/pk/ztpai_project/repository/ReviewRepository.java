package pl.edu.pk.ztpai_project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pk.ztpai_project.model.Review;
import pl.edu.pk.ztpai_project.model.Status;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStatus(Status status);
    List<Review> findAllByProductId(Long productId);
    void deleteAllByProductId(Long productId);
    Optional<Review> findByIdAndStatus(Long id, Status status);
    List<Review> getAllByProductIdAndStatus(Long productId, Status status);
}
