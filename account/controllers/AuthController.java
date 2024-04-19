package account.controllers;

import account.dto.AuthDTO;
import account.responses.BadRequestResponse;
import account.services.AuthService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody AuthDTO authRequest) {
        try {
            return ResponseEntity.ok(authService.signUpUser(authRequest).toString());
        } catch (ValidationException exception) {
            BadRequestResponse badRequestResponse = new BadRequestResponse("/api/signup");
            System.out.println(exception.getMessage());
           return ResponseEntity.badRequest().body(badRequestResponse.getStatus());
        }
    }
}
