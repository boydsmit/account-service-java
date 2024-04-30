package account.security;

import account.database.UserRepository;
import account.models.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository = new UserRepository();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userDetails = userRepository.getUserByEmail(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(userDetails.getPassword());

        return org.springframework.security.core.userdetails.User.builder()
                .username(userDetails.getName())
                .password(encodedPassword)
                .roles("USER")
                .build();
    }
}
