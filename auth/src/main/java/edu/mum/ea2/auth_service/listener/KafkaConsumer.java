//package edu.mum.ea3.auth_service.listener;
//
//import edu.mum.ea3.auth_service.models.Messages;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumer {
//
//    @KafkaListener(topics = "Kafka_Login", groupId = "group_id")
//    public void consume(String message){
//        System.out.println("Consumed message: " + message);
//    }
//
////    @KafkaListener(topics = "Kafka_User_Login_json", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
////    public void consumeJson(UserEntity userEntity){
////        System.out.println("Consumed JSON Message: " + userEntity);
////    }
//
//    @KafkaListener(topics = "Carpool_Token", groupId = "group_id")
//    public void consumeToken(Messages messages){
//        System.out.println("Get TOKEN message: " + messages);
//    }
//}
