package com.aurionpro.mappings.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;

import jakarta.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.ByteArrayResource;


import com.aurionpro.mappings.entity.Passbook;
import com.aurionpro.mappings.entity.Transaction;
import com.aurionpro.mappings.repository.PassbookRepository;
import com.aurionpro.mappings.repository.BankAccountRepository;

@Service
public class PassbookServiceImpl implements PassbookService {

    private static final Logger logger = LoggerFactory.getLogger(PassbookServiceImpl.class);

    @Autowired
    private PassbookRepository passbookRepo;

    @Autowired
    private BankAccountRepository bankAccountRepo;
    
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private EmailService emailService;
    
    
    public byte[] generatePassbookPdf(List<Passbook> passbooks) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Passbook").setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER));

            // Add passbooks to the PDF
            Table table = new Table(new float[]{1, 3, 3, 3, 3,3});
            table.addHeaderCell(new Cell().add(new Paragraph("Passbook ID")));
            table.addHeaderCell(new Cell().add(new Paragraph("Sender Account Number")));
            table.addHeaderCell(new Cell().add(new Paragraph("Receiver Account Number")));
            table.addHeaderCell(new Cell().add(new Paragraph("Amount")));
            table.addHeaderCell(new Cell().add(new Paragraph("Transaction Date")));
            table.addHeaderCell(new Cell().add(new Paragraph("Type")));

            for (Passbook passbook : passbooks) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(passbook.getPassbookId()))));
                table.addCell(new Cell().add(new Paragraph(passbook.getSenderAccountNumber())));
                table.addCell(new Cell().add(new Paragraph(passbook.getReceiverAccountNumber() != null ? passbook.getReceiverAccountNumber() : "N/A")));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(passbook.getAmount()))));
                table.addCell(new Cell().add(new Paragraph(passbook.getTransactionDate().toString())));
                table.addCell(new Cell().add(new Paragraph(passbook.getTransactionType())));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return outputStream.toByteArray();
    }

    public void sendPassbookEmail(String toEmail, byte[] passbookPdf) throws MessagingException {
        String subject = "Your Passbook Request";
        String body = "Dear Customer, \n\nPlease find attached your passbook.\n\nBest regards,\nBank";
        String filename = "Passbook.pdf";

        try {
			emailService.sendEmailWithAttachment(toEmail, subject, body, filename, passbookPdf);
		} catch (jakarta.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


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

    // Additional method to generate and send passbook
    public void generateAndSendPassbook(String toEmail, String senderAccountNumber) throws MessagingException, jakarta.mail.MessagingException {
        List<Passbook> passbooks = getPassbooksBySenderAccountNumber(senderAccountNumber);
        byte[] passbookPdf = generatePassbookPdf(passbooks);
        sendPassbookEmail(toEmail, passbookPdf);
    }
}