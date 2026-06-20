package com.example.spring_ai_ollama.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PdfService {

    String uploadPdf(MultipartFile file);

    List<String> getUploadedPdfList();
}