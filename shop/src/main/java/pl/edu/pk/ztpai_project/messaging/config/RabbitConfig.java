package pl.edu.pk.ztpai_project.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.rabbitmq.review-queue}")
    private String reviewQueue;

    @Value("${app.rabbitmq.reply-queue}")
    private String replyQueue;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.review-routing-key}")
    private String reviewRoutingKey;

    @Value("${app.rabbitmq.reply-routing-key}")
    private String replyRoutingKey;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue reviewQueue() {
        return new Queue(reviewQueue, true);
    }

    @Bean
    public Queue replyQueue() {
        return new Queue(replyQueue, true);
    }

    @Bean
    public Binding reviewBinding() {
        return BindingBuilder
                .bind(reviewQueue())
                .to(exchange())
                .with(reviewRoutingKey);
    }

    @Bean
    public Binding replyBinding() {
        return BindingBuilder
                .bind(replyQueue())
                .to(exchange())
                .with(replyRoutingKey);
    }
}
