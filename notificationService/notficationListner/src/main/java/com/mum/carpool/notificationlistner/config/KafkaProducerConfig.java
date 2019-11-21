package com.mum.carpool.notificationlistner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mum.carpool.notificationlistner.model.Notification;
import com.mum.carpool.notificationlistner.repository.NotificationRepository;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap.servers}")
    String kafkaAdress;

    private final String KAFKA_URI;

    public KafkaProducerConfig(@Value("${notfication.service.kafkaUri: 127.0.0.1:9092}") String kafka_uri) {
        KAFKA_URI = kafka_uri;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public KafkaTemplate<String, Notification> orderKafkaTemplate(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URI);
        ProducerFactory<String, Notification> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<Notification>(objectMapper));

        return new KafkaTemplate<>(producerFactory);
    }
}
