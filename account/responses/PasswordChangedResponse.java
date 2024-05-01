package account.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PasswordChangedResponse {
    private String email;
    private String status;

    public PasswordChangedResponse(String email) {
        this.email = email;
        this.status = "The password has been updated successfully";
    }

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = objectMapper.createObjectNode();

            jsonObject.put("email", email);
            jsonObject.put("status", status);
            return objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
