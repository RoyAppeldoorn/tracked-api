package com.tracked.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
public class User implements UserDetails {

    private static final long serialVersionUID = 4815877135015943617L;

//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    //TODO add unique index
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
