package com.aurionpro.mappings.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	 Optional<Role> findByRolename(String rolename);
}
