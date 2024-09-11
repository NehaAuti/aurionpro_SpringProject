package com.aurionpro.mappings.cloudera.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.mappings.cloudera.entity.Document;
import com.aurionpro.mappings.cloudera.service.DocumentServiceImpl;


@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentServiceImpl documentServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
        Document document = documentServiceImpl.uploadDocument(file);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

}