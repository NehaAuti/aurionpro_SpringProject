package com.aurionpro.jpacurd.entity.services;

import com.aurionpro.jpacurd.dto.PageResponsePayment;
import com.aurionpro.jpacurd.entity.Payment;
import com.aurionpro.jpacurd.entity.PaymentMode;
import com.aurionpro.jpacurd.entity.PaymentStatus;

public interface PaymentService {

    PageResponsePayment<Payment> getAllPayments(int pageno, int pagesize);

    void addPayment(Payment payment);

    void deletePayment(int paymentId);

	Payment getByPaymentId(int paymentId);

	PageResponsePayment<Payment> getAllPaymentsByPaymentMode(PaymentMode paymentMode, int pageno, int pagesize);
}