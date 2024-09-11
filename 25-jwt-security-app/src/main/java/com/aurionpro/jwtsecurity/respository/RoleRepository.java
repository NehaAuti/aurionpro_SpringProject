package com.aurionpro.jwtsecurity.respository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.jwtsecurity.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRolename(String role);
}
