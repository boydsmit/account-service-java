package account.services;

import account.database.UserRepository;
import account.dto.AuthDTO;
import account.models.UserModel;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;

@Service
public class AuthService {

    private Validator validator = new Validator();
    private UserRepository userRepository = new UserRepository();

    public UserModel signUpUser(AuthDTO authDTO) throws ValidationException, KeyAlreadyExistsException {
        validator.validateJson(authDTO);
        validator.validateEmail(authDTO.getEmail());
        int id = userRepository.addUser(authDTO.getName(), authDTO.getLastname(), authDTO.getEmail(), authDTO.getPassword());
        return new UserModel(id, authDTO.getName(), authDTO.getLastname(), authDTO.getEmail(), authDTO.getPassword());
    }
}
