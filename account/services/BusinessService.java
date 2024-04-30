package account.services;

import account.database.UserRepository;
import account.models.UserModel;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    UserRepository userRepository = new UserRepository();

    public UserModel getPayment(String name) {
        return userRepository.getUserByName(name);
    }
}
