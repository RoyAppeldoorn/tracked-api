package com.tracked.api.controller.auth;

import com.tracked.api.dto.SignUpDto;
import com.tracked.api.service.impl.AuthServiceImpl;
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
public class SignUpController {

    private final AuthServiceImpl authService;

    @Autowired
    public SignUpController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity signUp(@RequestHeader(value="X-Firebase-Auth", required = false) String firebaseToken, @Valid @RequestBody SignUpDto signUpModel) {
        log.info(firebaseToken);
        if(firebaseToken == null) {
            return new ResponseEntity<>("No firebase token available in header", HttpStatus.UNAUTHORIZED);
        }

        return authService.signUpUser(firebaseToken, signUpModel);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        log.warn("Returning HTTP 400 Bad Request", e);
    }
}
