package account.controllers;

import account.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empl")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping("/payment")
    public ResponseEntity<String> getPayment(Authentication authentication) {
        return ResponseEntity.ok( businessService.getPayment(authentication.getName()).toString());
    }
}
