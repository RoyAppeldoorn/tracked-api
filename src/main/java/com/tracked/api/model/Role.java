package com.tracked.api.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "ROLE")
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = -8186644851823152209L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authority;

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
