package com.aurionpro.mappings.entity;

import java.security.SecureRandom;

public class AccountNumberGenerator {

    private static final String PREFIX = "ACC";
    private static final int DIGIT_COUNT = 7;
    private static final SecureRandom random = new SecureRandom();

    public static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder(PREFIX);
        for (int i = 0; i < DIGIT_COUNT; i++) {
            accountNumber.append(random.nextInt(10)); // Append a random digit
        }
        return accountNumber.toString();
    }
}