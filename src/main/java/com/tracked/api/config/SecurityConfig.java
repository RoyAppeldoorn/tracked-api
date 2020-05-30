package com.tracked.api.config;

import com.tracked.api.security.FirebaseAuthenticationEntryPoint;
import com.tracked.api.security.FirebaseAuthenticationProvider;
import com.tracked.api.security.FirebaseAuthenticationTokenFilter;
import com.tracked.api.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final FirebaseAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(FirebaseAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    @Qualifier(value = CustomUserDetailsService.NAME)
    private UserDetailsService userService;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/genre/**").permitAll()
                .antMatchers("/tracklist/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(new FirebaseAuthenticationEntryPoint());

        httpSecurity.addFilterBefore(tokenFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
    }

    private FirebaseAuthenticationTokenFilter tokenFilter() {
        return new FirebaseAuthenticationTokenFilter();
    }

}