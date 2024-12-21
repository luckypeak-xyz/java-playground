package xyz.luckypeak.playground.websocket_learning.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.luckypeak.playground.websocket_learning.config.GetHttpSessionConfigurator;
import xyz.luckypeak.playground.websocket_learning.entity.Message;
import xyz.luckypeak.playground.websocket_learning.utils.MessageUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j
public class ChatEndpoint {

    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    private HttpSession httpSession;

    @Resource
    private ObjectMapper objectMapper;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        onlineUsers.put((String) httpSession.getAttribute("user"), session);

        String msg = MessageUtils.getMessage(true, null, getFriends());
        broadcastAllUsers(msg);
    }

    private Set<String> getFriends() {
        return onlineUsers.keySet();
    }

    private void broadcastAllUsers(String msg) {
        onlineUsers.forEach((__, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                log.info("发送消息失败", e);
            }
        });
    }

    @OnMessage
    public void onMessage(String msg) {
        Message message;
        try {
            message = objectMapper.readValue(msg, Message.class);
        } catch (JsonProcessingException e) {
            log.error("消息解析失败", e);
            throw new RuntimeException(e);
        }
        String toName = message.getToName();
        String userMsg = message.getMessage();

        if (onlineUsers.containsKey(toName)) {
            Session toSession = onlineUsers.get(toName);
            String user = (String) httpSession.getAttribute("user");
            try {
                toSession.getBasicRemote().sendText(MessageUtils.getMessage(false, user, userMsg));
            } catch (IOException e) {
                log.info("发送消息失败", e);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        String user = (String) httpSession.getAttribute("user");
        onlineUsers.remove(user);

        String message = MessageUtils.getMessage(true, null, getFriends());
        broadcastAllUsers(message);
    }

}
