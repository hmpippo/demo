package org.example.kafka.serialize;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.kafka.message.Message;

public class MessageDeserializer implements Deserializer<Message> {

    @Override
    public Message deserialize(String topic, byte[] data) {
        return JSON.parseObject(data, Message.class);
    }
}
