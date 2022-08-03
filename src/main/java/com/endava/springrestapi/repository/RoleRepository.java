package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Role;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface RoleRepository extends JpaRepositoryImplementation<Role,Integer> {
    Role findByName(String name);
}
