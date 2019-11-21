package edu.mum.reservetrip.kafkaConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.reservetrip.model.Trip;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.internals.Sender;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReserveProducerConfig {

    @Autowired
    private ObjectMapper objectMapper;

    private final String KAFKA_URI;

    public ReserveProducerConfig(@Value("${reservation.service.kafkaUri: 127.0.0.1:9092}") String kafka_uri) {
        KAFKA_URI = kafka_uri;
    }




    @Bean
    public KafkaTemplate<String, Trip> orderKafkaTemplate(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URI);
        ProducerFactory<String, Trip> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<>(objectMapper));

        return new KafkaTemplate<>(producerFactory);
    }

}
