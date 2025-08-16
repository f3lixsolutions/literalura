package com.felixsolutions.literalura.service;

import com.felixsolutions.literalura.dto.GutendexBookDTO;
import com.felixsolutions.literalura.dto.GutendexResponseDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Service
public class GutendexClient {
    private static final String BASE = "https://gutendex.com/books/?search=";
    private final HttpClient http = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<GutendexBookDTO> buscarPorTitulo(String titulo) {
        try {
            String q = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            HttpRequest req = HttpRequest.newBuilder(URI.create(BASE + q)).GET().build();
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            GutendexResponseDTO dto = gson.fromJson(resp.body(), GutendexResponseDTO.class);
            return dto != null && dto.getResults() != null ? dto.getResults() : Collections.emptyList();
        } catch (Exception e) {
            System.out.println("Falha ao consultar Gutendex: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
