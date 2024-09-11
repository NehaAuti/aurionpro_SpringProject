package com.aurionpro.mappings.service;

import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.entity.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
 
    String sendMailWithAttachment(EmailDetails details);
  
}