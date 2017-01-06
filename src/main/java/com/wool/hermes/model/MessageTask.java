package com.wool.hermes.model;

import com.google.gson.Gson;
import com.wool.hermes.exception.QueueMessageException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by wanglin on 17-1-6.
 */
public class MessageTask {

    private String id;
    private int cmd;
    private String payload;

    public MessageTask(String payload) {
        this.id = UUID.randomUUID().toString();
        this.cmd = -1;
        this.payload = payload;
    }

    public MessageTask(int cmd, String payload) {
        this.id = UUID.randomUUID().toString();
        this.cmd = cmd;
        this.payload = payload;
    }

    public static MessageTask fromAmqpMessage(Message message) {
        Gson gson = new Gson();
        MessageTask messageTask = null;
        try {
            messageTask = gson.fromJson(new String(message.getBody(), "utf-8"), MessageTask.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new QueueMessageException();
        }

        return messageTask;
    }

    public Message toAmqpMessage() {
        Gson gson = new Gson();
        Message message = null;

        try {
            message = MessageBuilder.withBody(gson.toJson(this).getBytes("utf-8")).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new QueueMessageException();
        }
        return message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String toString() {
        return String.format("<MessageTask: %s, %s, '%s'>", id, cmd, payload);
    }
}
