package pl.edu.pk.ztpai_project.messaging.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import pl.edu.pk.ztpai_project.messaging.event.ReviewProcessedEvent;
import pl.edu.pk.ztpai_project.model.Review;
import pl.edu.pk.ztpai_project.model.Status;
import pl.edu.pk.ztpai_project.repository.ReviewRepository;

@Service
public class ReviewReplyListener {
    private final ReviewRepository reviewRepository;

    public ReviewReplyListener(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @RabbitListener(queues = "${app.rabbitmq.reply-queue}")
    public void handleReply(ReviewProcessedEvent event) {

        System.out.println("Reply received:");
        System.out.println("Review ID: " + event.id());
        System.out.println("Status: " + event.status());

        Review review = reviewRepository.findById(event.id())
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + event.id()));

        Status newStatus;

        if ("APPROVED".equalsIgnoreCase(event.status())) {
            newStatus = Status.ACCEPTED;
        } else {
            newStatus = Status.REJECTED;
        }

        review.setStatus(newStatus);
        reviewRepository.save(review);
    }
}
