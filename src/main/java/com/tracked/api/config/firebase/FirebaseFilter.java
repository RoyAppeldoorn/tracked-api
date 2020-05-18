package com.tracked.api.config.firebase;

import com.tracked.api.service.FirebaseService;
import com.tracked.api.service.exception.FirebaseTokenInvalidException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class FirebaseFilter extends OncePerRequestFilter {

    private final FirebaseService firebaseService;

    public FirebaseFilter(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("X-Authorization-Firebase");
        String username = null;
        FirebaseTokenHolder holder = null;

        if (authorizationHeader != null) {
            try {
                holder = firebaseService.parseToken(authorizationHeader);
                username = holder.getUid();
            } catch (FirebaseTokenInvalidException e) {
                throw new SecurityException(e);
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication auth = new FirebaseAuthenticationToken(username, holder);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
