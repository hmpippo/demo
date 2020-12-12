package org.example.kafka;

import org.example.kafka.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaProducerTest {

    @Autowired
    private KafkaProducer producer;


    @Test
    public void testProduce() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Message msg = new Message();
            msg.setId(Long.valueOf(i));
            msg.setMsg("test" + String.valueOf(i));
            msg.setSendTime(new Date());
            producer.send(msg);
        }

        Thread.sleep(120000);
    }

    @Test
    @Transactional
    public void testTransaction() {
        Message msg = new Message();
        msg.setId(1024l);
        msg.setMsg("test transaction!");
        msg.setSendTime(new Date());
        producer.send(msg);
        throw new RuntimeException("fail");
    }


    @Test
    public void testSendWithinTransaction() {

        producer.sendWithinTransaction((kafkaTemplate, topic) -> {
            Message msg = new Message();
            msg.setId(2048l);
            msg.setMsg("test send within transaction!");
            msg.setSendTime(new Date());
            kafkaTemplate.send(topic, msg);

            throw new RuntimeException("fail");
        });
    }


    @Test
    public void testExecuteInTransaction() {

        producer.executeInTransaction((kafkaTemplate, topic) -> {
            Message msg = new Message();
            msg.setId(4096l);
            msg.setMsg("test execute in transaction!");
            msg.setSendTime(new Date());
            kafkaTemplate.executeInTransaction(kafkaOperations -> {
                kafkaOperations.send(topic, msg);
                throw new RuntimeException("fail");
            });
        });
    }

}
