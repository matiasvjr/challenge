package itau.iti.challenge.controller;

import itau.iti.challenge.password.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("password")
public class PasswordController {

    private final Logger log = LoggerFactory.getLogger(PasswordController.class);

    private final PasswordService passwordService;

    public PasswordController(final PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("validate")
    public ResponseEntity<Boolean> validate(@RequestParam final String password) {
        log.debug("New request to validate password: " + password);

        final boolean valid = passwordService.isValid(password);

        log.debug("Password '" + password + "' is valid? " + valid);

        return ResponseEntity.ok(valid);
    }
}
