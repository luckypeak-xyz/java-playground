package xyz.luckypeak.playground.websocket_learning.entity;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String username;
    private String password;
}
