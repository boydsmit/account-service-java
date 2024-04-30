package account.controllers;

import account.database.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class actuator {
    private UserRepository userRepository = new UserRepository();

    @PostMapping("/actuator/shutdown")
    public void shutdown(){
        userRepository.dropAll();
        System.exit(1);
    }
}
