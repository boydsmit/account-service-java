package account.controllers;

import account.dto.AuthDTO;
import account.dto.ChangePassDTO;
import account.models.UserModel;
import account.responses.BadRequestResponse;
import account.responses.PasswordChangedResponse;
import account.services.AuthService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.openmbean.KeyAlreadyExistsException;

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
            BadRequestResponse badRequestResponse = new BadRequestResponse("/api/signup", exception.getMessage());
            System.out.println(exception.getMessage());
            return ResponseEntity.badRequest().body(badRequestResponse.getStatus());
        } catch (KeyAlreadyExistsException exception) {
            BadRequestResponse badRequestResponse = new BadRequestResponse("/api/signup", "User exist!");
            return ResponseEntity.badRequest().body(badRequestResponse.getStatus());
        }
    }

    @PostMapping("changepass")
    public ResponseEntity<String> changePass(@RequestBody ChangePassDTO newPass, Authentication authentication) {
        try {
            UserModel user = authService.changePassword(newPass.getNewPassword(), authentication.getName());
            PasswordChangedResponse passwordChangedResponse = new PasswordChangedResponse(user.getEmail());
            return ResponseEntity.ok(passwordChangedResponse.toString());
        } catch (ValidationException validationException) {
            BadRequestResponse badRequestResponse = new BadRequestResponse("/api/auth/changepass",  validationException.getMessage());
            return ResponseEntity.badRequest().body(badRequestResponse.getStatus());
        }
    }
}
