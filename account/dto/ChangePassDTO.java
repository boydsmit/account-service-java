package account.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePassDTO {
    private String newPassword;

    @JsonCreator
    public ChangePassDTO(@JsonProperty("new_password") String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
