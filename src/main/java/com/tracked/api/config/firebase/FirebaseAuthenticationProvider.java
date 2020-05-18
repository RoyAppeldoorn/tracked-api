package com.tracked.api.config.firebase;

import com.tracked.api.config.firebase.exception.FirebaseUserDoesNotExistException;
import com.tracked.api.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier(value = UserServiceImpl.NAME)
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername(authenticationToken.getName());
        if(user == null) {
            throw new FirebaseUserDoesNotExistException();
        }

        authenticationToken = new FirebaseAuthenticationToken(user, authentication.getCredentials(),
                user.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (FirebaseAuthenticationToken.class.isAssignableFrom(aClass));
    }
}