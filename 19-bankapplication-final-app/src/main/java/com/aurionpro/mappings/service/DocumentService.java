package com.aurionpro.mappings.service;



import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.entity.Document;




public interface DocumentService {


	 Document uploadKycDocument(MultipartFile file, String type, int customerId) throws IOException;

	 Document updateKycDocumentStatus(Long documentId, String status) ;

	 List<Document> getCustomerKycDocuments(int customerId);

	 Document getDocumentById(Long documentId);
	 
	 List<Document> getKycDocumentStatus(int customerId);
}
