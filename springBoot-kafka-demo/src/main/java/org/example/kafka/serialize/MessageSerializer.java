package org.example.kafka.serialize;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;
import org.example.kafka.message.Message;

public class MessageSerializer implements Serializer<Message> {

    @Override
    public byte[] serialize(String topic, Message data) {
        return JSON.toJSONBytes(data);
    }
}
