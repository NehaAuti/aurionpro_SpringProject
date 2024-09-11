package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCustomer(Customer customer);
}
