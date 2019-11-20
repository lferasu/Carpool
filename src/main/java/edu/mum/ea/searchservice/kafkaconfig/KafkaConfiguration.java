// package edu.mum.ea.searchservice.kafkaconfig;


// import com.fasterxml.jackson.databind.ObjectMapper;
// import edu.mum.ea.searchservice.model.Trip;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import java.util.HashMap;
// import java.util.Map;
// import org.springframework.kafka.support.serializer.JsonSerializer;
// import org.apache.kafka.common.serialization.StringSerializer;
// import org.apache.kafka.clients.producer.ProducerConfig;
// import org.springframework.kafka.core.DefaultKafkaProducerFactory;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.kafka.core.ProducerFactory;


// @Configuration
// public class  KafkaConfiguration {
//     @Autowired
//     private ObjectMapper objectMapper;

//     @Bean
//     public KafkaTemplate<String, Trip> orderKafkaTemplate(){
//         Map<String, Object> props = new HashMap<>();
//         props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//         ProducerFactory<String, Trip> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<Trip>(objectMapper));
//         return new KafkaTemplate<>(producerFactory);
//     }
// }