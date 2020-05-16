package com.tracked.api.config.firebase;

import com.tracked.api.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private FirebaseAuthenticationProvider firebaseProvider;
    private FirebaseService firebaseService;

    public SecurityConfig(FirebaseAuthenticationProvider firebaseProvider, FirebaseService firebaseService) {
        this.firebaseProvider = firebaseProvider;
        this.firebaseService = firebaseService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(firebaseProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class).authorizeRequests().anyRequest().authenticated();
    }

    private FirebaseFilter tokenAuthorizationFilter() {
        return new FirebaseFilter(firebaseService);
    }
}
