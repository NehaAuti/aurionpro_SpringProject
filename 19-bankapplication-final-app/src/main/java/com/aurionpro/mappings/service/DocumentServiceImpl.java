package com.aurionpro.mappings.service;



import java.io.IOException;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Document;
import com.aurionpro.mappings.repository.CustomerRepository;
import com.aurionpro.mappings.repository.DocumentRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Document uploadKycDocument(MultipartFile file, String type, int customerId) throws IOException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        String url = (String) uploadResult.get("url");
        String fileName = file.getOriginalFilename();

        Document document = new Document();
        document.setName(fileName);
        document.setUrl(url);
        document.setType(type);
        document.setStatus("PENDING");
        document.setCustomer(customer);

        return documentRepository.save(document);
    }

    @Override
    public Document updateKycDocumentStatus(Long documentId, String status) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));
        document.setStatus(status);
        return documentRepository.save(document);
    }

    @Override
    public List<Document> getCustomerKycDocuments(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return documentRepository.findByCustomer(customer);
    }

    @Override
    public List<Document> getKycDocumentStatus(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return documentRepository.findByCustomer(customer);
    }
    
    @Override
    public Document getDocumentById(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));
    }
}
