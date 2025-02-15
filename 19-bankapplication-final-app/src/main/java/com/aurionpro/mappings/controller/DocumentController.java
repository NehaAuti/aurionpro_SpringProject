
package com.aurionpro.mappings.controller;

import java.io.IOException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.entity.Document;
import com.aurionpro.mappings.service.DocumentService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/bankapp/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
    

    @PostMapping("/upload")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Document> uploadKycDocument(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("type") String type,
                                                      @RequestParam("customerId") int customerId) throws IOException {
        Document document = documentService.uploadKycDocument(file, type, customerId);
        return ResponseEntity.ok(document);
    }
    
    @GetMapping("/kycstatus/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Document>> getKycDocumentStatus(@PathVariable int customerId) {
        logger.info("Fetching KYC document status for customerId: {}", customerId);
        
        List<Document> documents = documentService.getKycDocumentStatus(customerId);
        
        logger.info("Fetched {} documents for customerId: {}", documents.size(), customerId);
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/{documentId}/verify")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Document> updateDocumentStatus(@PathVariable Long documentId,
                                                         @RequestParam("status") String status) {
        Document updatedDocument = documentService.updateKycDocumentStatus(documentId, status);
        return ResponseEntity.ok(updatedDocument);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Document>> getCustomerDocuments(@PathVariable int customerId) {
        List<Document> documents = documentService.getCustomerKycDocuments(customerId);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{documentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long documentId) {
        Document document = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(document);
    }
}
