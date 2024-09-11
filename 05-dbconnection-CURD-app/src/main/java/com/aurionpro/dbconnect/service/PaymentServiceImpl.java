package com.aurionpro.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dbconnect.entity.Payment;
import com.aurionpro.dbconnect.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public List<Payment> getAllPayment() {
		// TODO Auto-generated method stub
		return paymentRepo.getAllPayment();
	}

	@Override
	public Payment getPayment(int paymentId) {
		// TODO Auto-generated method stub
		return paymentRepo.getPayment(paymentId);
	}

	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.addPayment(payment);
		
	}

	@Override
	public void deletePayment(int paymentId) {
		// TODO Auto-generated method stub
		paymentRepo.deletePayment(paymentId);
		
	}

}
