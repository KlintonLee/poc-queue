package com.poc.queue.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    @ConfigurationProperties("amqp.queues.image-created")
    public QueueProperties imageCreatedQueueProperties() {
        return new QueueProperties();
    }

    /**
     * Configuração para criar a exchange, queue e binding para o evento de imagem.
     * É como se fosse Infra as Code, só vai criar de fato quando tiver uma conexão ativa com o rabbitmq.
     */
    @Configuration
    static class Admin {
        @Bean
        public Exchange imageCreatedExchange(QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        public Queue imageCreatedQueue(QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        public Binding imageCreatedBinding(
                final Queue imageCreatedQueue,
                final DirectExchange imageCreatedExchange,
                final QueueProperties props) {
            return BindingBuilder.bind(imageCreatedQueue)
                    .to(imageCreatedExchange)
                    .with(props.getRoutingKey());
        }
    }
}
