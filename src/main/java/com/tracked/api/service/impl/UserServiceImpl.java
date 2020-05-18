package com.tracked.api.service.impl;

import com.tracked.api.model.Role;
import com.tracked.api.model.User;
import com.tracked.api.repository.RoleRepository;
import com.tracked.api.repository.UserRepository;
import com.tracked.api.service.UserService;
import com.tracked.api.service.shared.RegisterUserInit;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Service(value = UserServiceImpl.NAME)
@Log4j2
public class UserServiceImpl implements UserService {

    public final static String NAME = "UserService";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(username);
        if (userDetails == null)
            return null;

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (GrantedAuthority role : userDetails.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
    }

    private Role getRole(String authority) {
        Role adminRole = roleRepository.findByAuthority(authority);
        if (adminRole == null) {
            return new Role(authority);
        } else {
            return adminRole;
        }
    }

}
