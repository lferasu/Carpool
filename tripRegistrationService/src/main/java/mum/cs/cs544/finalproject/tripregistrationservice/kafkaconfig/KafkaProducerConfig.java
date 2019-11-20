
package mum.cs.cs544.finalproject.tripregistrationservice.kafkaconfig;
import mum.cs.cs544.finalproject.tripregistrationservice.model.SearchTripKafka;
import mum.cs.cs544.finalproject.tripregistrationservice.model.Trip;
import org.springframework.kafka.annotation.EnableKafka;
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
@EnableKafka
public class KafkaProducerConfig {

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private ObjectMapper objectMapper2;

    @Bean
    public KafkaTemplate<String, Trip> orderKafkaTemplate(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        ProducerFactory<String, Trip> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<Trip>(objectMapper));
        return new KafkaTemplate<>(producerFactory);
    }


    @Bean
    public KafkaTemplate<String, SearchTripKafka> orderKafkaTemplate2(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        ProducerFactory<String, SearchTripKafka> producerFactory = new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new JsonSerializer<SearchTripKafka>(objectMapper2));
        return new KafkaTemplate<>(producerFactory);
    }
}