package com.aurionpro.mappings.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

 
    List<Client> findByCompanyName(String companyName);

    Optional<Client> findByRegistrationNumber(int registrationNumber);
}
