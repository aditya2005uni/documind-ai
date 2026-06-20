package com.example.spring_ai_ollama.service;

import com.example.spring_ai_ollama.helper.PdfChunkUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PdfServiceImpl implements PdfService {

    private final VectorStore vectorStore;

    public PdfServiceImpl(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public String uploadPdf(MultipartFile file) {

        try {

            PDDocument document =
                    PDDocument.load(file.getInputStream());

            PDFTextStripper stripper =
                    new PDFTextStripper();

            String text = stripper.getText(document);

            document.close();

            List<String> chunks =
                    PdfChunkUtil.chunkText(text, 1000);

            System.out.println("Total Chunks : " + chunks.size());

            List<Document> documents =
                    chunks.stream()
                            .map(chunk ->
                                    new Document(
                                            chunk,
                                            java.util.Map.of(
                                                    "fileName",
                                                    file.getOriginalFilename()
                                            )
                                    ))
                            .toList();

            vectorStore.add(documents);

            return "PDF uploaded and stored successfully. Total Chunks: "
                    + chunks.size();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error reading PDF";
        }
    }

    @Override
    public List<String> getUploadedPdfList() {

        List<Document> documents =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query("")
                                .topK(1000)
                                .build()
                );

        Set<String> pdfNames = new HashSet<>();

        for (Document doc : documents) {

            Object fileName =
                    doc.getMetadata().get("fileName");

            if (fileName != null) {
                pdfNames.add(fileName.toString());
            }
        }

        return pdfNames.stream().toList();
    }
}




//package com.example.spring_ai_ollama.service;
//
//import com.example.spring_ai_ollama.helper.PdfChunkUtil;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.springframework.ai.document.Document;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Service
//public class PdfServiceImpl implements PdfService {
//
//    private final VectorStore vectorStore;
//
//    public PdfServiceImpl(VectorStore vectorStore) {
//        this.vectorStore = vectorStore;
//    }
//
//    @Override
//    public String uploadPdf(MultipartFile file) {
//
//        try {
//
//            PDDocument document =
//                    PDDocument.load(file.getInputStream());
//
//            PDFTextStripper stripper =
//                    new PDFTextStripper();
//
//            String text = stripper.getText(document);
//
//            document.close();
//
//            // Split PDF into chunks
//            List<String> chunks =
//                    PdfChunkUtil.chunkText(text, 1000);
//
//            System.out.println("Total Chunks : " + chunks.size());
//
//            // Convert chunks into Documents
////            List<Document> documents = chunks.stream()
////                    .map(Document::new)
////                    .toList();
//
//            List<Document> documents =
//                    chunks.stream()
//                            .map(chunk ->
//                                    new Document(
//                                            chunk,
//                                            java.util.Map.of(
//                                                    "fileName",
//                                                    file.getOriginalFilename()
//                                            )
//                                    ))
//                            .toList();
//
//            // Store into Vector Database
//            vectorStore.add(documents);
//
//            return "PDF uploaded and stored successfully. Total Chunks: "
//                    + chunks.size();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error reading PDF";
//        }
//    }
//}
