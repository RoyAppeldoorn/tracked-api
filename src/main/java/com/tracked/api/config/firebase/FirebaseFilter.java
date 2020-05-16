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

    private static String HEADER_NAME = "X-Authorization-Firebase";

    private final FirebaseService firebaseService;

    public FirebaseFilter(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String xAuth = request.getHeader(HEADER_NAME);
        if (xAuth.isBlank()) {
            filterChain.doFilter(request, response);
            log.info("No header with name " + HEADER_NAME + " given");
        } else {
            try {
                log.info("Header with name " + HEADER_NAME + " given");
                FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);

                String userName = holder.getUid();

                Authentication auth = new FirebaseAuthenticationToken(userName, holder);
                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
            } catch (FirebaseTokenInvalidException e) {
                throw new SecurityException(e);
            }
        }
    }
}
