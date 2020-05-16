package com.tracked.api.service.impl;

import com.tracked.api.model.Role;
import com.tracked.api.model.User;
import com.tracked.api.repository.RoleRepository;
import com.tracked.api.repository.UserRepository;
import com.tracked.api.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = UserServiceImpl.NAME)
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

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            User adminEntity = new User();
            adminEntity.setUsername("admin");
            adminEntity.setPassword("admin");
            adminEntity.setEmail("roy.appeldoorn@gmail.com");

            adminEntity.setAuthorities(getAdminRoles());
            userRepository.save(adminEntity);

            User userEntity = new User();
            userEntity.setUsername("user1");
            userEntity.setPassword("user1");
            userEntity.setEmail("roy.appeldoorn@gaymail.com");
            userEntity.setAuthorities(getUserRoles());

            userRepository.save(userEntity);
        }
    }

    private List<Role> getAdminRoles() {
        return Collections.singletonList(getRole("ROLE_ADMIN"));
    }

    private List<Role> getUserRoles() {
        return Collections.singletonList(getRole("ROLE_USER"));
    }

    /**
     * Get or create role
     * @param authority
     * @return
     */
    private Role getRole(String authority) {
        Role adminRole = roleRepository.findByAuthority(authority);
        if (adminRole == null) {
            return new Role(authority);
        } else {
            return adminRole;
        }
    }

}
