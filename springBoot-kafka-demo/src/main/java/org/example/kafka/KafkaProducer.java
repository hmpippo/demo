package org.example.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Component
public class KafkaProducer {

    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Object msg) {
        // String msg2String = JSONObject.toJSONString(msg);
        log.info("准备发送消息为：{}", msg);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("topic: {} - 生产者 发送消息失败：{}", topic, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("topic: {} - 生产者 发送消息成功：{}", topic, result);
            }
        });
    }

    // 声明式事务
    @Transactional(rollbackFor = Exception.class)
    public void sendWithinTransaction(BiConsumer<KafkaTemplate, String> consumer) {
        consumer.accept(kafkaTemplate, topic);
    }

    // 本地事务
    public void executeInTransaction(BiConsumer<KafkaTemplate, String> consumer) {
        consumer.accept(kafkaTemplate, topic);
    }


}
