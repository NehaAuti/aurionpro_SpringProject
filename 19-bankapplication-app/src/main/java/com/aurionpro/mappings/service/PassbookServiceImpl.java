package com.aurionpro.mappings.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Passbook;
import com.aurionpro.mappings.repository.PassbookRepository;
import com.aurionpro.mappings.repository.BankAccountRepository;

@Service
public class PassbookServiceImpl implements PassbookService {

    private static final Logger logger = LoggerFactory.getLogger(PassbookServiceImpl.class);

    @Autowired
    private PassbookRepository passbookRepo;

    @Autowired
    private BankAccountRepository bankAccountRepo;

    @Override
    public List<Passbook> getPassbooksBySenderAccountNumber(String senderAccountNumber) {
        logger.info("Fetching passbook records for account number: {}", senderAccountNumber);

        // Check if the account exists
        if (!bankAccountRepo.existsByAccountNumber(senderAccountNumber)) {
            logger.warn("Account number not found: {}", senderAccountNumber);
            throw new IllegalArgumentException("Account number not found.");
        }

        // Fetch passbook records for the sender account number
        List<Passbook> passbookRecords = passbookRepo.findBySenderAccountNumber(senderAccountNumber);
        logger.info("Found {} passbook records for account number: {}", passbookRecords.size(), senderAccountNumber);

        return passbookRecords;
    }
}
