package com.example.spring_ai_ollama.controller;

import com.example.spring_ai_ollama.service.PdfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                pdfService.uploadPdf(file)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getUploadedPdfs() {

        return ResponseEntity.ok(
                pdfService.getUploadedPdfList()
        );
    }
}



//package com.example.spring_ai_ollama.controller;
//
//import com.example.spring_ai_ollama.service.PdfService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/pdf")
//public class PdfController {
//
//    private final PdfService pdfService;
//
//    public PdfController(PdfService pdfService) {
//        this.pdfService = pdfService;
//    }
//
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadPdf(
//            @RequestParam("file") MultipartFile file) {
//
//        return ResponseEntity.ok(
//                pdfService.uploadPdf(file)
//        );
//    }
//}