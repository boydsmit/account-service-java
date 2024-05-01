package account.services;

import account.database.UserRepository;
import account.dto.AuthDTO;
import account.models.UserModel;
import jakarta.validation.ValidationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;

@Service
public class AuthService {

    private final Validator validator = new Validator();
    private final UserRepository userRepository = new UserRepository();

    public UserModel signUpUser(AuthDTO authDTO) throws ValidationException, KeyAlreadyExistsException {
        validator.validateJson(authDTO);
        validator.validatePassword(authDTO.getPassword());
        validator.validateEmail(authDTO.getEmail());
        int id = userRepository.addUser(authDTO.getName(), authDTO.getLastname(), authDTO.getEmail(), authDTO.getPassword());
        return new UserModel(id, authDTO.getName(), authDTO.getLastname(), authDTO.getEmail(), authDTO.getPassword());
    }

    public UserModel changePassword(String newPassword, String username) throws ValidationException {
        validator.validatePassword(newPassword);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserModel user = userRepository.getUserByName(username);
        if(encoder.matches(newPassword, user.getPassword())) {
            throw new ValidationException("The passwords must be different!");
        }
        userRepository.ChangeUserPassword(newPassword, user.getEmail());
        return user;
    }
}
