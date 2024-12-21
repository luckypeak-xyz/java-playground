package xyz.luckypeak.playground.websocket_learning.config;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    /**
     * 获取HttpSession，并将其设置到userProperties中，以便在Endpoint中使用
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest req, HandshakeResponse resp) {
        HttpSession httpSession = (HttpSession) req.getHttpSession();
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }

}
