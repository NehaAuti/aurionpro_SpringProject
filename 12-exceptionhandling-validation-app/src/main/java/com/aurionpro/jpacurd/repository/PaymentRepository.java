package com.aurionpro.jpacurd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacurd.entity.Payment;
import com.aurionpro.jpacurd.entity.PaymentMode;
import com.aurionpro.jpacurd.entity.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Page<Payment> findByPaymentMode(PaymentMode paymentMode, Pageable pageable);

}
