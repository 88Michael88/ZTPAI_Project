package pl.edu.pk.ztpai_project.messaging.listener;

import pl.edu.pk.ztpai_project.messaging.event.ReviewCreatedEvent;
import pl.edu.pk.ztpai_project.messaging.event.ReviewProcessedEvent;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewConsumer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.reply-routing-key}")
    private String replyRoutingKey;

    public ReviewConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "${app.rabbitmq.review-queue}")
    public void consume(ReviewCreatedEvent event) {

        System.out.println("Received review: " + event.review());

        // Process review
        String status = event.review().length() > 10
                ? "APPROVED"
                : "REJECTED";

        ReviewProcessedEvent reply = new ReviewProcessedEvent(
                event.id(),
                event.productId(),
                event.review(),
                status
        );

        rabbitTemplate.convertAndSend(
                exchange,
                replyRoutingKey,
                reply
        );
    }
}
