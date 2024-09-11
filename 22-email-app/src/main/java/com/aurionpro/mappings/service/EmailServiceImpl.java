package com.aurionpro.mappings.service;
 
import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.entity.EmailDetails;

// Importing required classes


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
 
// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {
 
    @Autowired private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}") private String sender;
 
    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {
 
        // Try block to check for exceptions
      
 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
    }
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            // Add attachment
            if (details.getAttachment() != null) {
                FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
                mimeMessageHelper.addAttachment(file.getFilename(), file);
            }

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

    }
   