package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.entity.Passbook;
import com.aurionpro.mappings.entity.Transaction;

import jakarta.mail.MessagingException;

public interface PassbookService {
	List<Passbook> getPassbooksBySenderAccountNumber(String senderAccountNumber);

	 byte[] generatePassbookPdf(List<Passbook> passbooks) throws MessagingException, javax.mail.MessagingException;

	 void generateAndSendPassbook(String toEmail, String senderAccountNumber) throws MessagingException, jakarta.mail.MessagingException, javax.mail.MessagingException ;
}
