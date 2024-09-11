package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.mappings.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{

}