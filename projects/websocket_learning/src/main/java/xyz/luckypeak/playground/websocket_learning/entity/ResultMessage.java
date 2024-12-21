package xyz.luckypeak.playground.websocket_learning.entity;

import lombok.Data;

@Data
public class ResultMessage {

    private boolean isSystem;

    private String fromName;

    private Object message;

}
