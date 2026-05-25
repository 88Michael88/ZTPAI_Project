package pl.edu.pk.ztpai_project.messaging.publisher;

import pl.edu.pk.ztpai_project.messaging.event.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReviewReplyPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.reply-routing-key}")
    private String routingKey;

    public ReviewReplyPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishReply(ReviewProcessedEvent event) {

        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                event
        );
    }
}
