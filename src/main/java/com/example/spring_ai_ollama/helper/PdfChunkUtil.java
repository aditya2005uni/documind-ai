package com.example.spring_ai_ollama.helper;

import java.util.ArrayList;
import java.util.List;

public class PdfChunkUtil {

    public static List<String> chunkText(String text, int chunkSize) {

        List<String> chunks = new ArrayList<>();

        for (int i = 0; i < text.length(); i += chunkSize) {

            int end = Math.min(i + chunkSize, text.length());

            chunks.add(text.substring(i, end));
        }

        return chunks;
    }
}