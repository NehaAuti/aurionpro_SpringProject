package com.aurionpro.email.service;

import com.aurionpro.email.entity.*;
 

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
 
    String sendMailWithAttachment(EmailDetails details);
  
}