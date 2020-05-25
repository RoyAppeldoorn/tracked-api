package com.tracked.api.controller.auth;

import com.tracked.api.model.projection.IRegister;
import com.tracked.api.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Log4j2
public class RegisterController {

    private final AuthService authService;

    @Autowired
    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestHeader(value="X-Firebase-Auth", required = false) String firebaseToken, @Valid @RequestBody IRegister registerModel) {
        log.info(firebaseToken);
        if(firebaseToken == null) {
            return new ResponseEntity<>("No firebase token available in header", HttpStatus.UNAUTHORIZED);
        }

        return authService.registerUser(firebaseToken, registerModel);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        log.warn("Returning HTTP 400 Bad Request", e);
    }
}
