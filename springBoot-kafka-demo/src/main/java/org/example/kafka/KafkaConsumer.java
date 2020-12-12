package org.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.kafka.message.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.consumer.topic}")
    public void consume1(ConsumerRecord<String, Message> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional<Message> opt = Optional.ofNullable(record.value());
        if (opt.isPresent()) {
            Message msg = opt.get();
//            try {
//                // simulate biz logic
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            log.info("consumer1 消费了 topic: {}, message: {}", topic, msg.getMsg());
            ack.acknowledge();
        }
    }

//    @KafkaListener(topics = {KafkaCommon.TOPIC}, groupId = KafkaCommon.GROUP2)
//    public void consume2(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//
//        Optional opt = Optional.ofNullable(record.value());
//        if (opt.isPresent()) {
//            Object msg = opt.get();
//            log.info("consumer2 消费了 topic: {}, message: {}", topic, msg);
//            ack.acknowledge();
//        }
//    }
}
