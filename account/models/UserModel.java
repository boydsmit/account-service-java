package account.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserModel {
    private String name;
    private String lastname;
    private String email;

    public UserModel(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
