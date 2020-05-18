package com.tracked.api.config.firebase;

import com.tracked.api.config.firebase.exception.InvalidFirebaseTokenException;
import org.springframework.security.core.Authentication;
import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.tracked.api.config.firebase.model.FirebaseUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseAuthenticationTokenFilter extends OncePerRequestFilter {

    private static String HEADER_NAME = "X-Firebase-Auth";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String idToken = req.getHeader(HEADER_NAME);

        if(idToken != null) {
            ApiFuture<FirebaseToken> task = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken);
            try {
                FirebaseToken token = task.get();
                FirebaseUserDetails holder = new FirebaseUserDetails(token);

                String uid = holder.getUid();
                Authentication auth = new FirebaseAuthenticationToken(uid, holder);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        filterChain.doFilter(req, res);
    }
}