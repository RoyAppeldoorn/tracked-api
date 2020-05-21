package com.tracked.api.service.impl;

import com.tracked.api.config.firebase.model.FirebaseUserDetails;
import com.tracked.api.dto.SignUpDto;
import com.tracked.api.model.User;
import com.tracked.api.repository.RoleRepository;
import com.tracked.api.repository.UserRepository;
import com.tracked.api.service.AuthService;
import com.tracked.api.service.FirebaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Log4j2
public class AuthServiceImpl {

    private final UserRepository userRepository;

    private final FirebaseService firebaseService;

    private final RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, FirebaseService firebaseService, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.firebaseService = firebaseService;
    }

    public ResponseEntity signUpUser(String firebaseToken, SignUpDto signUpModel) {
        try {
            FirebaseUserDetails tokenHolder = firebaseService.parseToken(firebaseToken);

            if(userRepository.existsById(tokenHolder.getUid())) {
                return new ResponseEntity<>("User already exists with given id", HttpStatus.FORBIDDEN);
            }

            User user = new User();
            user.setId(tokenHolder.getUid());
            user.setEmail(tokenHolder.getEmail());
            user.setUsername(signUpModel.getNickname());
            user.setAuthorities(Arrays.asList(roleRepository.findByAuthority("ROLE_USER")));
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>("Invalid firebase token", HttpStatus.UNAUTHORIZED);
        }

    }
}
