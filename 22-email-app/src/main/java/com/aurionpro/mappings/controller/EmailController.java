package com.aurionpro.mappings.controller;
 
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.entity.EmailDetails;
import com.aurionpro.mappings.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestParam("recipient") String recipient,
                                            @RequestParam("subject") String subject,
                                            @RequestParam("msgBody") String msgBody) {
        EmailDetails details = new EmailDetails();
        details.setRecipient(recipient);
        details.setSubject(subject);
        details.setMsgBody(msgBody);

        String status = emailService.sendSimpleMail(details);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity<String> sendMailWithAttachment(
            @RequestParam("recipient") String recipient,
            @RequestParam("subject") String subject,
            @RequestParam("msgBody") String msgBody,
            @RequestParam("attachment") MultipartFile attachment) {

        EmailDetails details = new EmailDetails();
        details.setRecipient(recipient);
        details.setSubject(subject);
        details.setMsgBody(msgBody);

        // Save the attachment to a temporary file
        if (!attachment.isEmpty()) {
            try {
                File tempFile = File.createTempFile("attachment", attachment.getOriginalFilename());
                attachment.transferTo(tempFile);
                details.setAttachment(tempFile.getAbsolutePath());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save attachment");
            }
        }

        String status = emailService.sendMailWithAttachment(details);
        return ResponseEntity.ok(status);
    }
}
