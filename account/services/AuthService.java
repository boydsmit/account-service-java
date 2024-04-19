package account.services;

import account.dto.AuthDTO;
import account.models.UserModel;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private Validator validator = new Validator();

    public UserModel signUpUser(AuthDTO authDTO) throws ValidationException {
        validator.validateJson(authDTO);
        validator.validateEmail(authDTO.getEmail());
        return new UserModel(authDTO.getName(), authDTO.getLastname(), authDTO.getEmail());
    }
}
