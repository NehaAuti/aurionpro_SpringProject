package com.aurionpro.dbconnect.service;

import java.util.List;

import com.aurionpro.dbconnect.entity.Payment;

public interface PaymentService {
	List<Payment> getAllPayment();
    Payment getPayment(int paymentId);
    void addPayment(Payment payment);
    void deletePayment(int paymentId);


}
