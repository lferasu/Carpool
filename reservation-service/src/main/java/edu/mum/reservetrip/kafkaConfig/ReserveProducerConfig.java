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

//    @Value("${kafka.bootstrap.servers}")
//    private String bootstrapServers;
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return properties;
//    }
//
//
//
//    @Bean
//    public ProducerFactory<String, Trip> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, Trip> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }




    @Autowired
    private ObjectMapper objectMapper;

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<Object,Trip> kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<Object,Trip> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

    @Bean
    public KafkaTemplate<String, Trip> orderKafkaTemplate(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        ProducerFactory<String, Trip> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<Trip>(objectMapper));

        return new KafkaTemplate<>(producerFactory);
    }

}
