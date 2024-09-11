package com.aurionpro.dbconnect.repository;

import java.util.List;

import com.aurionpro.dbconnect.entity.Payment;


public interface PaymentRepository {
	List<Payment> getAllPayment();
    Payment getPayment(int paymentId);
    void addPayment(Payment payment);
    void deletePayment(int paymentId);

}
