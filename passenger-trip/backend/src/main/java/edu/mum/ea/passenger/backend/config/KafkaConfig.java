package edu.mum.ea.passenger.backend.config;

import edu.mum.ea.passenger.backend.entity.TripEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private final String KAFKA_URI;

    public KafkaConfig(@Value("${passenger.trip.kafkaUri: 127.0.0.1:9092}") String kafka_uri) {
        KAFKA_URI = kafka_uri;
//        KAFKA_URI = "carpoolkafka:9092";
    }


    @Bean
    public KafkaTemplate<String, TripEntity> orderKafkaTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URI);
        ProducerFactory<String, TripEntity> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<TripEntity>(objectMapper));
        return new KafkaTemplate<>(producerFactory);
    }


}