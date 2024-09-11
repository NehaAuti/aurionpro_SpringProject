package com.aurionpro.mappings.cloudera.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.cloudera.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}