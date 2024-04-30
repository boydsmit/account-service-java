package account.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BadRequestResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;

    private String message;

    public BadRequestResponse(String path) {
        this.timestamp = LocalDateTime.now();
        this.status = 400;
        this.error =  "Bad Request";
        this.path = path;
    }

    public BadRequestResponse(String path, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = 400;
        this.error =  "Bad Request";
        this.path = path;
        this.message = message;
    }

    public String getStatus() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = objectMapper.createObjectNode();

            jsonObject.put("timestamp", timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
            jsonObject.put("status", status);
            jsonObject.put("error", error);
            if (message != null) {
                jsonObject.put("message", message);
            }
            jsonObject.put("path", path);
            return objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
