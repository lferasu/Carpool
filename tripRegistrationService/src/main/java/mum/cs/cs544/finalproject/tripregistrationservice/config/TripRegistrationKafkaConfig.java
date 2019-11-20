package mum.cs.cs544.finalproject.tripregistrationservice.config;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.StringSerializer;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
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
public class TripRegistrationKafkaConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public KafkaTemplate<String, Trip> orderKafkaTemplate(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        ProducerFactory<String, Trip> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<Trip>(objectMapper));
        return new KafkaTemplate<>(producerFactory);
    }
}