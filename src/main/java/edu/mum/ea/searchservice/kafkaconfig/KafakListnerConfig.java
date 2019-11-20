// package edu.mum.ea.searchservice.kafkaconfig;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import edu.mum.ea.searchservice.model.TripData;
// import org.apache.kafka.clients.consumer.ConsumerConfig;
// import org.apache.kafka.clients.producer.ProducerConfig;
// import org.apache.kafka.common.serialization.StringDeserializer;
// import org.apache.kafka.common.serialization.StringSerializer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.jackson.JsonObjectDeserializer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.annotation.EnableKafka;
// import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
// import org.springframework.kafka.core.*;
// import org.springframework.kafka.support.serializer.JsonDeserializer;

// import java.util.HashMap;
// import java.util.Map;

// @EnableKafka
// @Configuration
// public class KafakListnerConfig {

//     @Bean
//     public ConsumerFactory<String, TripData> consumerFactory(){
//         JsonDeserializer<TripData> deserializer = new JsonDeserializer<>(TripData.class);

//         //deserializer.setRemoveTypeHeaders(false);
//       //  deserializer.addTrustedPackages("*");
//         //deserializer.setUseTypeMapperForKey(true);

//         Map<String, Object> config = new HashMap<>();

//         config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//         config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_one");
//         config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//         config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//         config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//         config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,deserializer);

//         return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
//     }

//     @Bean
//     public ConcurrentKafkaListenerContainerFactory<String, TripData> kafkaListenerContainerFactory(){
//         ConcurrentKafkaListenerContainerFactory<String, TripData> factory = new ConcurrentKafkaListenerContainerFactory<>();
//         factory.setConsumerFactory(consumerFactory());
//         return factory;
//     }


// }