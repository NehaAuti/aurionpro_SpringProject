package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.entity.Passbook;

public interface PassbookService {
	List<Passbook> getPassbooksBySenderAccountNumber(String senderAccountNumber);

}
