package com.aurionpro.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Passbook;

@Repository
public interface PassbookRepository extends JpaRepository<Passbook, Integer> {
	List<Passbook> findBySenderAccountNumber(String senderAccountNumber);

}
