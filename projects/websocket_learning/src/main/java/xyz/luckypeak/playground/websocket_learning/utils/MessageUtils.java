package xyz.luckypeak.playground.websocket_learning.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.luckypeak.playground.websocket_learning.entity.ResultMessage;

public class MessageUtils {

    public static String getMessage(boolean isSystemMessage, String fromName, Object message) {
        ResultMessage result = new ResultMessage();
        result.setSystem(isSystemMessage);
        result.setMessage(message);
        if (fromName != null) {
            result.setFromName(fromName);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

}
