package com.aurionpro.mappings.cloudera.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.cloudera.entity.Document;
import com.aurionpro.mappings.cloudera.repository.DocumentRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class DocumentServiceImpl {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private DocumentRepository documentRepository;

    public Document uploadDocument(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), 	// Upload the file to Cloudinary and retrieve the upload result
                ObjectUtils.asMap("resource_type", "auto"));

        String url = (String) uploadResult.get("url");
        String fileName = file.getOriginalFilename();

        Document document = new Document();
        document.setName(fileName);
        document.setUrl(url);

        return documentRepository.save(document);
    }
}
