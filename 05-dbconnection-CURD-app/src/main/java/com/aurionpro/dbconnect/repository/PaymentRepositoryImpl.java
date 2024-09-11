package com.aurionpro.dbconnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dbconnect.entity.Loan;
import com.aurionpro.dbconnect.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

	@Autowired
	private EntityManager manager;
	@Override
	public List<Payment> getAllPayment() {
		// TODO Auto-generated method stub
		TypedQuery<Payment> query = manager.createQuery("select p from Payment p",Payment.class);
		return query.getResultList();
	}

	@Override
	public Payment getPayment(int paymentId) {
		// TODO Auto-generated method stub
		return manager.find(Payment.class, paymentId);
	}

	@Transactional
	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		manager.merge(payment);
		
	}

	@Transactional
	@Override
	public void deletePayment(int paymentId) {
		// TODO Auto-generated method stub
		Payment payment = manager.find(Payment.class, paymentId);
        if (payment != null) {
            manager.remove(payment);
        }
		
	}

}
