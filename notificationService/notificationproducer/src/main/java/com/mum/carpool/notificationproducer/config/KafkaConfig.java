package com.mum.carpool.notificationproducer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mum.carpool.notificationproducer.model.Notification;
import com.mum.carpool.notificationproducer.model.Trip;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

//    @Bean
//    public ProducerFactory<String, Trip> producerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public ProducerFactory<String, String> stringProducerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Trip> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    KafkaTemplate<String, String> kafkaStringTemplate() {
//        return new KafkaTemplate<>(stringProducerFactory());
@Autowired
private ObjectMapper objectMapper;

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<Object,Trip> kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<Object,Trip> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

    @Bean
    public KafkaTemplate<Trip, Trip> orderKafkaTemplate(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        ProducerFactory<Trip, Trip> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<Trip>(objectMapper));

        return new KafkaTemplate<>(producerFactory);
    }
//    }

}
