package com.tracked.api.security;

import com.tracked.api.security.model.FirebaseAuthenticationToken;
import com.tracked.api.security.service.CustomUserDetailsService;
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
    @Qualifier(value = CustomUserDetailsService.NAME)
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername(authenticationToken.getName());
        if(user != null) {
            authenticationToken = new FirebaseAuthenticationToken(user, authentication.getCredentials(),
                    user.getAuthorities());
        } else {
            authenticationToken = new FirebaseAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
        }

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (FirebaseAuthenticationToken.class.isAssignableFrom(aClass));
    }
}