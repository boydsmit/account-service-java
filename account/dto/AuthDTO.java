package account.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthDTO {

    private String name;
    private String lastname;
    private String email;
    private String password;

    @JsonCreator
    public AuthDTO(@JsonProperty(value = "name") String name, @JsonProperty(value = "lastname") String lastname,
                   @JsonProperty(value = "email") String email, @JsonProperty(value = "password") String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }
}
