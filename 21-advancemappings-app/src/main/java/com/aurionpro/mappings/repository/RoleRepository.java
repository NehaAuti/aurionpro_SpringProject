package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
