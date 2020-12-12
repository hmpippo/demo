package org.example.kafka.message;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private Long Id;

    private String msg;

    private Date sendTime;
}
