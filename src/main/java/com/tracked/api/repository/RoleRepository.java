package com.tracked.api.repository;

import com.tracked.api.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByAuthority(String authority);

}
