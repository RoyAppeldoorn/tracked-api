package com.tracked.api.service;

import com.tracked.api.security.model.FirebaseUserDetails;
import com.tracked.api.model.projection.IRegister;
import com.tracked.api.model.User;
import com.tracked.api.repository.RoleRepository;
import com.tracked.api.repository.UserRepository;
import com.tracked.api.security.service.FirebaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Log4j2
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final FirebaseService firebaseService;

    @Autowired
    public AuthService(UserRepository userRepository, FirebaseService firebaseService, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.firebaseService = firebaseService;
    }

    public ResponseEntity registerUser(String firebaseToken, IRegister registerModel) {
        try {
            FirebaseUserDetails tokenHolder = firebaseService.parseToken(firebaseToken);
            String tokenHolderEmail = tokenHolder.getEmail();
            if(userRepository.existsByEmail(tokenHolderEmail)) {
                return new ResponseEntity<>("User already exists", HttpStatus.FORBIDDEN);
            }

            User user = new User();
            user.setId(tokenHolder.getUid());
            user.setEmail(tokenHolder.getEmail());
            user.setNickname(registerModel.getNickname());
            user.setAuthorities(Collections.singletonList(roleRepository.findByAuthority("ROLE_USER")));
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>("Invalid firebase token", HttpStatus.UNAUTHORIZED);
        }

    }
}
