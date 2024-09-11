package com.aurionpro.jpacurd.entity.services;

import java.util.Optional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacurd.dto.PageResponsePayment;
import com.aurionpro.jpacurd.entity.Payment;
import com.aurionpro.jpacurd.entity.PaymentMode; // Ensure this is correct
import com.aurionpro.jpacurd.entity.PaymentStatus;
import com.aurionpro.jpacurd.exception.PaymentNotFoundException;
import com.aurionpro.jpacurd.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;
    
    @Override
    public PageResponsePayment<Payment> getAllPayments(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Payment> paymentPage = paymentRepo.findAll(pageable);
        
        // Log or print the retrieved data
        System.out.println("Payments Retrieved: " + paymentPage.getContent());

        PageResponsePayment<Payment> paymentPageResponse = new PageResponsePayment<>();
        paymentPageResponse.setTotalPages(paymentPage.getTotalPages());
        paymentPageResponse.setSize(paymentPage.getSize());
        paymentPageResponse.setTotalElements(paymentPage.getTotalElements());
        paymentPageResponse.setLastPage(paymentPage.isLast());
        paymentPageResponse.setContent(paymentPage.getContent());
        return paymentPageResponse;
    }


    @Override
    public void addPayment(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    public Payment getByPaymentId(int paymentId) {
        Optional<Payment> dbPayment = paymentRepo.findById(paymentId);
        if (!dbPayment.isPresent()) {
            throw new PaymentNotFoundException();
        }
        return dbPayment.get();
    }

    @Override
    public void deletePayment(int paymentId) {
    	Optional<Payment> dbPayment = paymentRepo.findById(paymentId);
        if (!dbPayment.isPresent()) {
            throw new PaymentNotFoundException();
        }
        paymentRepo.deleteById(paymentId);
    }

    @Override
    public PageResponsePayment<Payment> getAllPaymentsByPaymentMode(PaymentMode paymentMode, int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Payment> paymentPage = paymentRepo.findByPaymentMode(paymentMode, pageable);
        
        PageResponsePayment<Payment> paymentPageResponse = new PageResponsePayment<>();
        paymentPageResponse.setTotalPages(paymentPage.getTotalPages());
        paymentPageResponse.setSize(paymentPage.getSize());
        paymentPageResponse.setTotalElements(paymentPage.getTotalElements());
        paymentPageResponse.setLastPage(paymentPage.isLast());
        paymentPageResponse.setContent(paymentPage.getContent());
        return paymentPageResponse;
    }

	
}